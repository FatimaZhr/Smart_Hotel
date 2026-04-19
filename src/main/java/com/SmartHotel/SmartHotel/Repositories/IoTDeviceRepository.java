package com.SmartHotel.SmartHotel.Repositories;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IoTDeviceRepository extends JpaRepository<IoTDevice, Long> {

    List<IoTDevice> findByRoom(Room room);

    List<IoTDevice> findByIsOnlineTrue();

    Optional<IoTDevice> findByDeviceUid(String deviceUid);
}
