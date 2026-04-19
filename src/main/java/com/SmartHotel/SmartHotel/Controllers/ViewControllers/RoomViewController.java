package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RoomViewController {

    @Autowired
    RoomService roomService;

    @GetMapping("/rooms")
    public String roomsPage(HttpSession session,Model model) {
        List<Room> rooms = roomService.getAllRooms();

        User loggedUser = (User) session.getAttribute("loggedUser");
        model.addAttribute("loggedUser", loggedUser);

        model.addAttribute("rooms", rooms);

        model.addAttribute("totalCount", rooms.size());
        model.addAttribute("availableCount",
                rooms.stream()
                        .filter(r -> r.getStatus() == RoomStatus.AVAILABLE)
                        .count());
        model.addAttribute("occupiedCount",
                rooms.stream()
                        .filter(r -> r.getStatus() == RoomStatus.OCCUPIED)
                        .count());

        return "rooms";
    }
}