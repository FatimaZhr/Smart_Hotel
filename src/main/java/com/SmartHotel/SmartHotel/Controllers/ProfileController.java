package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.services.ReservationService;
import com.SmartHotel.SmartHotel.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String profilePage(HttpSession session,
                              Model model) {

        User sessionUser = (User) session.getAttribute("loggedUser");
        if (sessionUser == null) return "redirect:/login";

        User user = userService.getUserById(sessionUser.getId());

        List<Reservation> myReservations =
                reservationService.getReservationsByGuest(user);

        model.addAttribute("loggedUser", user);
        model.addAttribute("myReservations", myReservations);
        return "profile";
    }
}