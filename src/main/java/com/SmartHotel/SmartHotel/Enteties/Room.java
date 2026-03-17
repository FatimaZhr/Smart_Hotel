package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Enums.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rooms")
public class Room {

    // Getters & Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    private Integer floor;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    private Double basePrice;
    private Boolean hasIot;

    // Methods
    public Boolean isAvailable() {
        return this.status == RoomStatus.AVAILABLE;
    }

    public Double getPrice() {
        return this.basePrice;
    }

}