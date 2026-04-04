package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import com.SmartHotel.SmartHotel.services.ReservationService;
import com.SmartHotel.SmartHotel.services.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationViewController {

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservations/new")
    public String newReservation(@RequestParam Long roomId, Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        return "reservation";
    }

    @PostMapping("/reservations/confirm")
    public String confirmReservation(
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            HttpSession session,
            Model model) {

        try {
            Room room = roomService.getRoomById(roomId);

            if (checkOutDate.isBefore(checkInDate)) {
                model.addAttribute("error", "Check-out date must be after check-in!");
                model.addAttribute("room", room);
                return "reservation";
            }


            User loggedUser = (User) session.getAttribute("loggedUser");

            Reservation reservation = Reservation.builder()
                    .room(room)
                    .guest(loggedUser)
                    .checkInDate(checkInDate)
                    .checkOutDate(checkOutDate)
                    .status(ReservStatus.CONFIRMED)
                    .build();

            reservationService.createReservation(reservation);
            return "redirect:/reservations";

        } catch (Exception e) {
            model.addAttribute("error", "System error: " + e.getMessage());
            return "reservation";
        }
    }
}