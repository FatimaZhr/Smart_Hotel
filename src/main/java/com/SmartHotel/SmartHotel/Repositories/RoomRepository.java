package com.SmartHotel.SmartHotel.Repositories;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository
        extends JpaRepository<Room, Long> {

    List<Room> findByStatus(RoomStatus status);

    List<Room> findByType(RoomType type);

    List<Room> findByHasIotTrue();
}
