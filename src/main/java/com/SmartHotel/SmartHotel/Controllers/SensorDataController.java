package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.SensorData;
import com.SmartHotel.SmartHotel.Enums.SensorType;
import com.SmartHotel.SmartHotel.services.RoomService;
import com.SmartHotel.SmartHotel.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorDataController {

    @Autowired
    SensorDataService sensorDataService;

    @Autowired
    RoomService roomService;

    // POST /api/sensors
    @PostMapping
    public SensorData saveSensorData(
            @RequestBody SensorData data) {
        return sensorDataService.saveSensorData(data);
    }

    // GET /api/sensors/rooms/1/latest
    @GetMapping("/rooms/{roomId}/latest")
    public List<SensorData> getLatestData(
            @PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return sensorDataService.getLatestData(room);
    }

    // GET /api/sensors/rooms/1?type=TEMPERATURE
    @GetMapping("/rooms/{roomId}")
    public List<SensorData> getByType(
            @PathVariable Long roomId,
            @RequestParam SensorType type) {
        Room room = roomService.getRoomById(roomId);
        return sensorDataService.getByType(room, type);
    }
}


