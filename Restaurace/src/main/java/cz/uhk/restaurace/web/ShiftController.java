package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by dann on 14.12.2014.
 */

@Controller
public class ShiftController {

    @Autowired
    private UserService userService;

    /**
     * Display all cooks currently cooking and all cooks employed
     * @param model
     * @return
     */
    @RequestMapping(value = "/whosCooking")
    public String whoCooks(Model model){
        Role.RoleType cookRole = Role.RoleType.ROLE_COOK;
        Role.RoleType chefRole = Role.RoleType.ROLE_CHEF;

        //Process current time
        LocalDateTime currentTime = LocalDateTime.now();
        Integer hour = currentTime.getHour();
        String day = currentTime.getDayOfWeek().toString();

        //retrieve cooks based, on current time and role
        List<User> currentCooks = userService.getCooksCurrentlyCooking(
                hour, Shift.Day.valueOf(day), cookRole);

        model.addAttribute("currentCooks", currentCooks);

        // get all cooks and chef
        List<User> chefs = userService.getUsersByRole(chefRole);
        List<User> cooks = userService.getUsersByRole(cookRole);

        model.addAttribute("chefs", chefs);
        model.addAttribute("cooks", cooks);

        return "whosCooking";
    }
}
