package com.SmartHotel.SmartHotel;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enums.DeviceType;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Enums.RoomType;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(RoomService roomService, IoTDeviceService deviceService) {
        return args -> {
            System.out.println("###### Starting Data Seeding... ######");
            Room r101 = new Room();
                    if (roomService.getAllRooms().isEmpty()) {
                       r101 = Room.builder()
                                .roomNumber("101")
                                .floor(1)
                                .type(RoomType.SINGLE)
                                .status(RoomStatus.AVAILABLE)
                                .basePrice(500.0)
                                .hasIot(true)
                                .build();
                        roomService.createRoom(r101);
                        System.out.println("###### New data created! ######");
                    } else {
                        System.out.println("###### Data already exists, skipping seeding... ######");
                    }
            IoTDevice tempSensor = IoTDevice.builder()
                    .deviceUid("TEMP-RM101-01")
                    .room(r101) // الربط
                    .deviceType(DeviceType.TEMPERATURE)
                    .status("22.5°C")
                    .isOnline(true)
                    .build();

            deviceService.saveDevice(tempSensor);
            System.out.println("###########################################");
            System.out.println("###### SmartHotel System Initialized! ######");
            System.out.println("###########################################");
        };
    }
}