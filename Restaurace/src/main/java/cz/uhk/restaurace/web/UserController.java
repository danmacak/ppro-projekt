package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.AddressService;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(User user){
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegister(@Valid User user, BindingResult bindingResult){
        if (user == null || user.getUsername() == null){
            return "redirect:/";
        }
        if (bindingResult.hasErrors()){
            return "registration";
        }
        Collection<Role> role = new ArrayList<Role>(Arrays.asList(new Role(Role.RoleType.ROLE_USER)));
        user.setRoles(role);
        userService.addUser(user);
        return "redirect:/regsuccessful";
    }

    @RequestMapping(value = "/user/userdetails")
    public String getUserDetails(Model model, HttpSession session, Principal principal){
        return "user/userdetails";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        session.removeAttribute("user");
        return "redirect:/";
    }
}
