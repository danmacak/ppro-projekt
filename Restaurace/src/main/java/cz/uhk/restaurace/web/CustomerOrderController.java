package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/user/orders")
    public String getOrders(Model model, HttpSession session){
        return "user/orders";
    }

    @RequestMapping(value = "/checkout")
    public String checkout(Model model, HttpSession session){

        return "cartCheckout";
    }

    @RequestMapping(value = "/orderCart")
    public String orderCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        model.addAttribute("orderedDishes", dishes);
        model.addAttribute("deliveries", deliveryService.listDelivery());
        return "cartOrder";
    }

    @RequestMapping(value = "/emptyCart")
    public String emptyCart(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().clear();
        return "cart";
    }


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
        String dishName = "teppanyaki" + numOfTeppanyakis;
        cart.getOrderedTeppanyakiDishes().put(dishName, dish);
        session.removeAttribute("teppanyakiDish");
        return "cart";
    }

    //TODO dodelat test
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

    @RequestMapping(value = "/showCart")
    public String showCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        model.addAttribute("orderedDishes", dishes);
        return "cart";
    }

    /*@RequestMapping(value = "/removeItem")
    public String removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().remove(id);
        return "redirect:/showCart";
    }*/

    //TODO v budoucnu by to chtelo vracet Dish nebo CustomerOrder
    @RequestMapping(value = "/removeItem")
    @ResponseBody
    public Integer removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().remove(id);
        return id;
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
