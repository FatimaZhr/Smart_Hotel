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
    public List<IoTDevice> getDevicesByRoomId(Long roomId) {
        Room room = new Room();
        room.setId(roomId);
        return iotDeviceRepository.findByRoom(room);
    }

    public IoTDevice sendCommand(Long deviceId, String command) {
        IoTDevice device = iotDeviceRepository
                .findById(deviceId)
                .orElseThrow(() ->
                        new RuntimeException("Device not found"));
        if (!device.getIsOnline()) {
            throw new RuntimeException("Device hors ligne!");
        }

         switch (command) {
            case "UNLOCK"       -> device.setStatus("UNLOCKED");
            case "LOCK"         -> device.setStatus("LOCKED");
            case "LIGHTS_ON"    -> device.setStatus("ON");
            case "LIGHTS_OFF"   -> device.setStatus("OFF");
            case "AC_ON"        -> device.setStatus("ON");
            case "AC_OFF"       -> device.setStatus("OFF");
            case "TV_ON"        -> device.setStatus("ON");
            case "TV_OFF"       -> device.setStatus("OFF");
            case "CURTAIN_OPEN" -> device.setStatus("OPEN");
            case "CURTAIN_CLOSE"-> device.setStatus("CLOSED");
            default             -> device.setStatus(command); // thermostat
        }

        device.sendCommand(command);
        return iotDeviceRepository.save(device);
    }

    public List<IoTDevice> getAllDevices() {
        return iotDeviceRepository.findAll();
    }
}
