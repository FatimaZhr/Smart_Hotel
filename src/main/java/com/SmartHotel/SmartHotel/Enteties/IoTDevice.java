package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.DeviceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "iot_devices")
public class IoTDevice {


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Column(unique = true, nullable = false)
    private String deviceUid;

    @Setter
    @Getter
    private String status;
    @Setter
    private Boolean isOnline;

    private String lastCommand;
    private LocalDateTime lastCommandAt;


    public void sendCommand(String command) {
        this.lastCommand = command;
        this.lastCommandAt = LocalDateTime.now();
    }

}
