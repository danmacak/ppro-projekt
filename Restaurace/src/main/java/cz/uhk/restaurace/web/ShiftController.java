package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * Created by dann on 14.12.2014.
 */

@Controller
public class ShiftController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/whosCooking")
    public String whoCooks(Model model){
        List<User> chefs = userService.getUsersByRole(Role.RoleType.ROLE_CHEF);
        List<User> cooks = userService.getUsersByRole(Role.RoleType.ROLE_COOK);
        model.addAttribute("chefs", chefs);
        model.addAttribute("cooks", cooks);
        return "whosCooking";
    }
}
