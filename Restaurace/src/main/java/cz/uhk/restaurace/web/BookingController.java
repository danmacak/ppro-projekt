package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Booking;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.BookingService;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/bookings")
    public String getBookings(HttpSession session, Principal principal){

        return "user/bookings";
    }
}
