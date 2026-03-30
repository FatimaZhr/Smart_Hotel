package com.SmartHotel.SmartHotel.Controllers.ViewControllers;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.RoomService;
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

    // GET /iot/rooms/{roomId}
    @GetMapping("/iot/rooms/{roomId}")
    public String iotPage(@PathVariable Long roomId,
                          Model model) {
        Room room = roomService.getRoomById(roomId);
        List<IoTDevice> devices =
                iotDeviceService.getDevicesByRoomId(roomId);
        model.addAttribute("room", room);
        model.addAttribute("devices", devices);
        return "iot";
    }

    // POST /iot/devices/{id}/command
    @PostMapping("/iot/devices/{id}/command")
    public String sendCommand(@PathVariable Long id,
                              @RequestParam String command,
                              @RequestParam Long roomId) {
        iotDeviceService.sendCommand(id, command);
        return "redirect:/iot/rooms/" + roomId;
    }
}