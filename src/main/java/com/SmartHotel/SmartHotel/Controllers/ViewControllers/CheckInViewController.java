package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckInViewController {

    @Autowired
    ReservationService reservationService;

    // GET /reservations
    @GetMapping("/reservations")
    public String reservationsPage(Model model) {
        model.addAttribute("reservations",
                reservationService.getAllReservations());
        return "checkin";
    }

    // POST /checkin/{id}
    @PostMapping("/checkin/{id}")
    public String checkIn(@PathVariable Long id) {
        reservationService.checkIn(id);
        return "redirect:/reservations";
    }

    // POST /checkout/{id}
    @PostMapping("/checkout/{id}")
    public String checkOut(@PathVariable Long id) {
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