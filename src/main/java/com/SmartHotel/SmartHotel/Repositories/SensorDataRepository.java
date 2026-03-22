package com.SmartHotel.SmartHotel.Repositories;

import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.SensorData;
import com.SmartHotel.SmartHotel.Enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findByRoom(Room room);

    List<SensorData> findByRoomAndSensorType(Room room, SensorType sensorType);

    List<SensorData> findTop10ByRoomOrderByRecordedAtDesc(Room room);
}
