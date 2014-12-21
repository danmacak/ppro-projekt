package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private DishGeneralService dishGeneralService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeliveryService deliveryService;

    private String language = "cs";

    public void setLanguage(String language, HttpSession session) {
        this.language = language;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public String checkout(HttpSession session, Principal principal,
                           @RequestParam(value = "delivery", required = false) String delivery){
        String attr = "cart";
        CustomerOrder cart = (CustomerOrder)session.getAttribute(attr);
        if(!cart.getOrderedDishes().isEmpty() || !cart.getOrderedTeppanyakiDishes().isEmpty()) {
            if(principal != null) {
                cart.setCustomer(userService.getUserById(principal.getName()));
            }
            try {
                LocalDate currentDate = LocalDate.now();
                Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                cart.setDate(date);
                cart.setDelivery(deliveryService.getDeliveryById(delivery));
                cart.setTotalPrice(cart.getTotalPrice().add(deliveryService.getDeliveryById(delivery).getPrice()));
            }finally {
                customerOrderService.addOrder(cart);
            }
        }
        session.removeAttribute(attr);
        return "cartCheckout";
    }

    /**
     * Show second page of cart ordering, first is a cart.html, show deliveries and ordered dishes
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderCart")
    public String orderCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        Map<String, DishGeneral> teppanyakiDishes = dishGeneralService.getLocalizedTeppanyakiDishes(
                cart.getOrderedTeppanyakiDishes(), this.language);
        model.addAttribute("teppanyakiDishes", teppanyakiDishes);
        model.addAttribute("orderedDishes", dishes);
        model.addAttribute("deliveries", deliveryService.listDelivery());
        return "cartOrder";
    }

    /**
     * Empty a cart
     * @param session
     * @return
     */
    @RequestMapping(value = "/emptyCart")
    public String emptyCart(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().clear();
        cart.getOrderedTeppanyakiDishes().clear();
        return "cart";
    }

    /**
     * Add users custom dish to a cart, generate automatically name of this dish
     * @param session
     * @return
     */
    @RequestMapping(value = "/addTeppanyakiToCart", method = RequestMethod.GET)
    public String addTeppanyakiToCart(HttpSession session){
        DishGeneral dish = (DishGeneral) session.getAttribute("teppanyakiDish");
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        if (cart == null){
            cart = new CustomerOrder();
            session.setAttribute("cart", cart);
        }

        int numOfTeppanyakis = cart.getNumOfTeppanyakis();
        numOfTeppanyakis++;
        cart.setNumOfTeppanyakis(numOfTeppanyakis);
        //TODO doresit jmeno teppanyaki jidla
        String dishName = "Teppanyaki " + numOfTeppanyakis;
        dish.setName(dishName);
        dish.setPrice(dish.getCustomDishPrice());
        cart.getOrderedTeppanyakiDishes().put(dishName, dish);
        session.removeAttribute("teppanyakiDish");
        return "redirect:/showCart";
    }

    //TODO dodelat test

    /**
     * Add regular dish to cart, not usable for custom dishes
     * @param session
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value="/addToCart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody DishGeneral addToCart(HttpSession session, @RequestParam Integer id,
                                        @RequestParam String amount){
        CustomerOrder cart = createCartIfNull(session);
        DishGeneral dish = dishGeneralService.getDishById(id);
        dish.setAmount(Integer.parseInt(amount));
        //Prevent saving dishes with same id again in a map, instead increment amount of dishes already stored
        DishGeneral duplicity = cart.getOrderedDishes().get(dish.getId());
        if(duplicity == null) {
            cart.getOrderedDishes().put(dish.getId(), dish);
        }else{
            duplicity.setAmount(duplicity.getAmount() + dish.getAmount());
        }
        return dish;
    }

    /**
     * Show users cart with completely localized info about each item
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/showCart")
    public String showCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        model.addAttribute("orderedDishes", dishes);
        Map<String, DishGeneral> teppanyakiDishes = cart.getOrderedTeppanyakiDishes();
        model.addAttribute("teppanyakiDishes",
                dishGeneralService.getLocalizedTeppanyakiDishes(teppanyakiDishes, this.language));
        return "cart";
    }

    /*@RequestMapping(value = "/removeItem")
    public String removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().remove(id);
        return "redirect:/showCart";
    }*/

    /**
     * Remove item from cart using ajax
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeItem")
    @ResponseBody
    public DishGeneral removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        DishGeneral dish = cart.getOrderedDishes().get(id);
        cart.getOrderedDishes().remove(id);
        return dish;
    }

    /**
     * Remove custom item from cart using ajax
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeCustomItem")
    @ResponseBody
    public DishGeneral removeCustomItem(HttpSession session, @RequestParam("name") String id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        DishGeneral dish = cart.getOrderedTeppanyakiDishes().get(id);
        cart.getOrderedTeppanyakiDishes().remove(id);
        return dish;
    }

    private CustomerOrder createCartIfNull(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        if (cart == null) {
            cart = customerOrderService.createCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
