package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    // POST /api/reservations
    @PostMapping
    public Reservation createReservation(
            @RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    // PUT /api/reservations/1/checkin
    @PutMapping("/{id}/checkin")
    public Reservation checkIn(@PathVariable Long id) {
        return reservationService.checkIn(id);
    }

    // PUT /api/reservations/1/checkout
    @PutMapping("/{id}/checkout")
    public Reservation checkOut(@PathVariable Long id) {
        return reservationService.checkOut(id);
    }

    // PUT /api/reservations/1/cancel
    @PutMapping("/{id}/cancel")
    public Reservation cancel(@PathVariable Long id) {
        return reservationService.cancelReservation(id);
    }
}