package com.SmartHotel.SmartHotel.services;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus(RoomStatus.AVAILABLE);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateStatus(Long id, RoomStatus status) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));
        room.setStatus(status);
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));
    }
}