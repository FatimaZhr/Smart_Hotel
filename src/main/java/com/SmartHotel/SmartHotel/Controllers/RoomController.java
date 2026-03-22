package com.SmartHotel.SmartHotel.Controllers;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    // GET /api/rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // GET /api/rooms/available
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // POST /api/rooms
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // PUT /api/rooms/1/status
    @PutMapping("/{id}/status")
    public Room updateStatus(@PathVariable Long id,
                             @RequestParam RoomStatus status) {
        return roomService.updateStatus(id, status);
    }
}