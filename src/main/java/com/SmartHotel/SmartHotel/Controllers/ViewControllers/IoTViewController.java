package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import com.SmartHotel.SmartHotel.Enums.Role;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.ReservationService;
import com.SmartHotel.SmartHotel.services.RoomService;
import com.SmartHotel.SmartHotel.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IoTViewController {

    @Autowired
    IoTDeviceService iotDeviceService;

    @Autowired
    RoomService roomService;
    @Autowired
    ReservationService reservationService;

    // GET /iot/rooms/{roomId}
    @GetMapping("/iot/rooms/{roomId}")
    public String iotPage(@PathVariable Long roomId,
                          HttpSession session,
                          Model model) {

        User loggedUser = (User) session.getAttribute("loggedUser");
        Room room = roomService.getRoomById(roomId);

         if (loggedUser.getRole() == Role.GUEST) {

             boolean hasAccess = reservationService
                    .getAllReservations()
                    .stream()
                    .anyMatch(r ->
                            r.getRoom().getId().equals(roomId) &&
                                    r.getGuest() != null &&
                                    r.getGuest().getId().equals(loggedUser.getId()) &&
                                    (r.getStatus() == ReservStatus.CONFIRMED ||
                                            r.getStatus() == ReservStatus.CHECKED_IN)
                    );

            if (!hasAccess) {
                return "redirect:/rooms";
            }
        }

        List<IoTDevice> devices =
                iotDeviceService.getDevicesByRoomId(roomId);

        model.addAttribute("room", room);
        model.addAttribute("devices", devices);
        model.addAttribute("loggedUser", loggedUser);
        return "iot";
    }
    @PostMapping("/iot/devices/{id}/command")
    public String sendCommand(@PathVariable Long id,
                              @RequestParam String command,
                              @RequestParam Long roomId,
                              HttpSession session) {

        User loggedUser = (User) session.getAttribute("loggedUser");

         if (loggedUser.getRole() == Role.GUEST) {
            boolean hasAccess = reservationService
                    .getAllReservations()
                    .stream()
                    .anyMatch(r ->
                            r.getRoom().getId().equals(roomId) &&
                                    r.getGuest() != null &&
                                    r.getGuest().getId().equals(loggedUser.getId()) &&
                                    (r.getStatus() == ReservStatus.CONFIRMED ||
                                            r.getStatus() == ReservStatus.CHECKED_IN)
                    );

            if (!hasAccess) {
                return "redirect:/rooms";
            }
        }

        iotDeviceService.sendCommand(id, command);
        return "redirect:/iot/rooms/" + roomId;
    }

}