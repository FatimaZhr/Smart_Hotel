package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.SensorType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "sensor_data")
public class SensorData {

    // Getters & Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Getter
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    // Method
    @Getter
    private Double value;
    private String unit;
    @Getter
    private LocalDateTime recordedAt;

}