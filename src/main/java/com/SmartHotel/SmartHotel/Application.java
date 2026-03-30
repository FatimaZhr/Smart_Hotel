package com.SmartHotel.SmartHotel;

import com.SmartHotel.SmartHotel.Enteties.IoTDevice;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.DeviceType;
import com.SmartHotel.SmartHotel.Enums.Role;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Enums.RoomType;
import com.SmartHotel.SmartHotel.services.IoTDeviceService;
import com.SmartHotel.SmartHotel.services.RoomService;
import com.SmartHotel.SmartHotel.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    CommandLineRunner start(RoomService roomService,
                            IoTDeviceService deviceService,
                            UserService userService) {
        return args -> {
            if (roomService.getAllRooms().isEmpty()) {

                // ✅ Room
                Room r101 = Room.builder()
                        .roomNumber("101")
                        .floor(1)
                        .type(RoomType.SINGLE)
                        .status(RoomStatus.AVAILABLE)
                        .basePrice(500.0)
                        .hasIot(true)
                        .imageUrl("/images/single1.jfif")
                        .rating(4.5)
                        .build();
                roomService.createRoom(r101);
                Room r102 = Room.builder()
                        .roomNumber("102")
                        .floor(2)
                        .type(RoomType.DOUBLE)
                        .status(RoomStatus.AVAILABLE)
                        .basePrice(900.0)
                        .hasIot(true)
                        .imageUrl("/images/double1.jfif")
                        .rating(5.0)
                        .build();
                roomService.createRoom(r102);

                Room r103 = Room.builder()
                        .roomNumber("103")
                        .floor(3)
                        .type(RoomType.FAMILY)
                        .status(RoomStatus.AVAILABLE)
                        .basePrice(1400.0)
                        .hasIot(true)
                        .imageUrl("/images/family3.jfif")
                        .rating(4.5)
                        .build();
                roomService.createRoom(r103);

                User guest = User.builder()
                        .fullName("Ahmed Benali")
                        .email("ahmed@gmail.com")
                        .phone("0612345678")
                        .passwordHash("1234")
                        .role(Role.GUEST)
                        .createdAt(LocalDateTime.now())
                        .build();
                userService.createUser(guest);

                User manager = User.builder()
                        .fullName("Manager Hotel")
                        .email("manager@hotel.com")
                        .phone("0611111111")
                        .passwordHash("admin")
                        .role(Role.MANAGER)
                        .createdAt(LocalDateTime.now())
                        .build();
                userService.createUser(manager);


                IoTDevice tempSensor = IoTDevice.builder()
                        .deviceUid("TEMP-RM101-01")
                        .room(r101)
                        .deviceType(DeviceType.TEMPERATURE)
                        .status("22.5°C")
                        .isOnline(true)
                        .build();
                deviceService.saveDevice(tempSensor);

                System.out.println("### SmartHotel Initialized! ###");

            } else {
                System.out.println("### Data already exists! ###");
            }
        };
    }
}