package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.CustomerOrder;
import cz.uhk.restaurace.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dann on 21.12.2014.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping(value = "/adminHome")
    public String adminHome(){
        return "admin/adminHome";
    }

    @RequestMapping(value= "/newOrders")
    public String showNewOrders(Model model){
        List<CustomerOrder> regOrders = customerOrderService.getUnprocessedRegisteredCustomerOrders();
        model.addAttribute("newRegOrders", regOrders);
        List<CustomerOrder> notregOrders = customerOrderService.getUnprocessedNotregisteredCustomerOrders();
        model.addAttribute("newNotregOrders", notregOrders);
        return "admin/adminNewOrders";
    }

    @RequestMapping(value = "/showNewOrders", produces = "application/json")
    @ResponseBody
    public Integer getNewOrders(){
        List<CustomerOrder> regOrders = customerOrderService.getUnprocessedRegisteredCustomerOrders();
        List<CustomerOrder> notregOrders = customerOrderService.getUnprocessedNotregisteredCustomerOrders();
        return regOrders.size() + notregOrders.size();
    }
}
