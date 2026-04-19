package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.DeviceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@Entity
@AllArgsConstructor
@JsonIgnoreProperties("devices")
@NoArgsConstructor
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
