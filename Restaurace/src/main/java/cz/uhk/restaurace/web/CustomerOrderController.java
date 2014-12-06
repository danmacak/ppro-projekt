package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    private DishLocalizedService dishLocalizedService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeliveryService deliveryService;

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
        createCartIfNull(session);
        model.addAttribute("deliveries", deliveryService.listBooking());
        return "cartOrder";
    }

    @RequestMapping(value = "/emptyCart")
    public String emptyCart(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().clear();
        return "cart";
    }

    @RequestMapping(value = "/teppanyaki", method = RequestMethod.GET)
    public String showTeppanyakiHighLevel(HttpSession session) {
        if (session.getAttribute("ingredientTypes") == null) {
            IngredientGeneral.IngredientType[] ingredientTypes = IngredientGeneral.IngredientType
                    .values();
            session.setAttribute("ingredientTypes", ingredientTypes);
        }
        if (session.getAttribute("teppanyakiDish") == null) {
            DishGeneral dish = new DishGeneral();
            Map<Integer, IngredientGeneral> ingredients = new HashMap<Integer, IngredientGeneral>();
            dish.setIngredients(ingredients);
            dish.setDishCategory(DishGeneral.DishCategory.TEPPANYAKI);
            session.setAttribute("teppanyakiDish", dish);
        }
        return "teppanyaki";
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
        String dishName = "teppanyaki" + numOfTeppanyakis;
        dish.setName(dishName);
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
        DishGeneral duplicity = cart.getOrderedDishes().get(dish.getName());
        if(duplicity == null) {
            cart.getOrderedDishes().put(dish.getId(), dish);
        }else{
            duplicity.setAmount(duplicity.getAmount() + dish.getAmount());
        }
        return dish;
    }
    //TODO
    @RequestMapping(value = "/showCart")
    public String showCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        List<Integer> ids = new ArrayList<Integer>();
        for(Map.Entry<Integer, DishGeneral> entry : cart.getOrderedDishes().entrySet()){
            ids.add(entry.getValue().getId());
        }
        Map<String, DishLocalized> localizedDishes = dishLocalizedService.getDishesLocalizedInCart(ids);
        model.addAttribute("ordinalDishes", localizedDishes);
        return "cart";
    }

    @RequestMapping(value = "/removeItem")
    public String removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().remove(id);
        return "redirect:/showCart";
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
