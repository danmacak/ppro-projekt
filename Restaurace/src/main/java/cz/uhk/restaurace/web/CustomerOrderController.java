package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.CustomerOrderService;
import cz.uhk.restaurace.service.DeliveryService;
import cz.uhk.restaurace.service.DishService;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private DishService dishService;

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

    @RequestMapping(value = "/addTeppanyakiToCart", method = RequestMethod.GET)
    public String addTeppanyakiToCart(HttpSession session){
        Dish dish = (Dish) session.getAttribute("teppanyakiDish");
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
    public @ResponseBody Dish addToCart(HttpSession session, @RequestParam String name,
                                        @RequestParam String amount){
        CustomerOrder cart = createCartIfNull(session);
        Dish dish = dishService.getDishById(name);
        dish.setAmount(Integer.parseInt(amount));
        //Prevent saving dishes with same id again in a map, instead increment amount of dishes already stored
        Dish duplicity = cart.getOrderedDishes().get(dish.getName());
        if(duplicity == null) {
            cart.getOrderedDishes().put(dish.getName(), dish);
        }else{
            duplicity.setAmount(duplicity.getAmount() + dish.getAmount());
        }
        return dish;
    }

    @RequestMapping(value = "/showCart")
    public String showCart(HttpSession session){
        createCartIfNull(session);
        return "cart";
    }

    @RequestMapping(value = "/removeItem")
    public String removeItem(HttpSession session, @RequestParam("id") String id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().remove(id);
        return "cart";
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
