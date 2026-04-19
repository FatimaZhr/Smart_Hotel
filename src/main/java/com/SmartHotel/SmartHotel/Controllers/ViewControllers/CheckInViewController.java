package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.Role;
import com.SmartHotel.SmartHotel.services.ReservationService;
import com.SmartHotel.SmartHotel.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckInViewController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;

    // GET /reservations
    @GetMapping("/reservations")
    public String reservationsPage(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("loggedUser");

        model.addAttribute("loggedUser", loggedUser);

        List<Reservation> reservations;

        if (loggedUser.getRole() == Role.MANAGER ||
                loggedUser.getRole() == Role.RECEPTIONIST) {

            reservations = reservationService.getAllReservations();
        } else {
            User freshUser = userService.getUserById(loggedUser.getId());
            reservations = reservationService
                    .getReservationsByGuest(freshUser);        }

        model.addAttribute("reservations", reservations);
        model.addAttribute("loggedUser", loggedUser);
        return "checkin";
    }

    @PostMapping("/checkin/{id}")
    public String checkIn(@PathVariable Long id,
                          HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        Reservation res = reservationService.getReservationById(id);

        if (loggedUser.getRole() == Role.GUEST &&
                !res.getGuest().getId().equals(loggedUser.getId())) {
            return "redirect:/reservations";
        }

        reservationService.checkIn(id);
        return "redirect:/reservations";
    }

    @PostMapping("/checkout/{id}")
    public String checkOut(@PathVariable Long id,
                           HttpSession session) {
        User loggedUser = (User) session.getAttribute("loggedUser");
        Reservation res = reservationService.getReservationById(id);

        if (loggedUser.getRole() == Role.GUEST &&
                !res.getGuest().getId().equals(loggedUser.getId())) {
            return "redirect:/reservations";
        }

        reservationService.checkOut(id);
        return "redirect:/reservations";
    }

    // POST /reservations/{id}/cancel
    @PostMapping("/reservations/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}