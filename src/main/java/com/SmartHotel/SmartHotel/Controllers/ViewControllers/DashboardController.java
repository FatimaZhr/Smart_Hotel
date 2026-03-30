package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.ReservationService;
import com.SmartHotel.SmartHotel.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    RoomService roomService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    IoTDeviceService iotDeviceService;

    @GetMapping("/dashboard")
    public String dashboard(Model model,
                            HttpSession session) {

         List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("totalRooms", rooms.size());
        model.addAttribute("availableRooms",
                rooms.stream()
                        .filter(r -> r.getStatus() == RoomStatus.AVAILABLE)
                        .count());
        model.addAttribute("occupiedRooms",
                rooms.stream()
                        .filter(r -> r.getStatus() == RoomStatus.OCCUPIED)
                        .count());

        List<Reservation> reservations =
                reservationService.getAllReservations();
        model.addAttribute("totalReservations",
                reservations.size());
        model.addAttribute("checkedIn",
                reservations.stream()
                        .filter(r -> r.getStatus() == ReservStatus.CHECKED_IN)
                        .count());

        // Revenue
        Double revenue = reservations.stream()
                .filter(r -> r.getStatus() == ReservStatus.CHECKED_OUT)
                .mapToDouble(r -> r.getTotalPrice() != null ?
                        r.getTotalPrice() : 0)
                .sum();
        model.addAttribute("totalRevenue", revenue);


        model.addAttribute("recentReservations",
                reservations.stream()
                        .sorted((a, b) -> b.getId()
                                .compareTo(a.getId()))
                        .limit(5)
                        .collect(Collectors.toList()));

        // IoT devices online
        model.addAttribute("onlineDevices",
                iotDeviceService.getAllDevices().stream()
                        .filter(d -> d.getIsOnline())
                        .count());


        model.addAttribute("rooms", rooms);

// Occupancy Rate
        if (!rooms.isEmpty()) {
            long occupied = rooms.stream()
                    .filter(r -> r.getStatus() == RoomStatus.OCCUPIED)
                    .count();
            double rate = (occupied * 100.0) / rooms.size();
            model.addAttribute("occupancyRate",
                    String.format("%.0f", rate));
        }

        return "dashboard";
    }

}