package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/iot")
public class IoTDeviceController {

    @Autowired
    IoTDeviceService iotDeviceService;

    @Autowired
    RoomService roomService;

    // GET /api/iot/rooms/1/devices
    @GetMapping("/rooms/{roomId}/devices")
    public List<IoTDevice> getDevicesByRoom(
            @PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return iotDeviceService.getDevicesByRoom(room);
    }

    // POST /api/iot/devices/1/command?command=LIGHTS_ON
    @PostMapping("/devices/{deviceId}/command")
    public IoTDevice sendCommand(
            @PathVariable Long deviceId,
            @RequestParam String command) {
        return iotDeviceService.sendCommand(deviceId, command);
    }
}
