package com.SmartHotel.SmartHotel.services;
import org.springframework.stereotype.Service;
import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Repositories.IoTDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@Service
public class IoTDeviceService {

    @Autowired
    IoTDeviceRepository iotDeviceRepository;


    public List<IoTDevice> getDevicesByRoom(Room room) {
        return iotDeviceRepository.findByRoom(room);
    }

    public IoTDevice saveDevice(IoTDevice device) {
        return iotDeviceRepository.save(device);
    }


    public IoTDevice sendCommand(Long deviceId, String command) {
        IoTDevice device = iotDeviceRepository
                .findById(deviceId)
                .orElseThrow(() ->
                        new RuntimeException("Device not found"));
        if (!device.getIsOnline()) {
            throw new RuntimeException("Device offline!");
        }
        device.sendCommand(command);
        return iotDeviceRepository.save(device);
    }
}
