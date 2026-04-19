package com.SmartHotel.SmartHotel.services;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.SensorData;
import com.SmartHotel.SmartHotel.Enums.SensorType;
import com.SmartHotel.SmartHotel.Repositories.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorDataService {

    @Autowired
    SensorDataRepository sensorDataRepository;


    public SensorData saveSensorData(SensorData data) {
        data.setRecordedAt(LocalDateTime.now());
        return sensorDataRepository.save(data);
    }


    public List<SensorData> getLatestData(Room room) {
        return sensorDataRepository
                .findTop10ByRoomOrderByRecordedAtDesc(room);
    }


    public List<SensorData> getByType(Room room,
                                      SensorType type) {
        return sensorDataRepository
                .findByRoomAndSensorType(room, type);
    }
}

