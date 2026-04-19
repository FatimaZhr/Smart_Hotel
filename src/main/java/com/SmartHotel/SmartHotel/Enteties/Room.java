package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@JsonIgnoreProperties("room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    private Integer floor;
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    private Double basePrice;
    private Boolean hasIot;

    private String imageUrl;

    private Double rating;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER) // ضروري تكون EAGER دابا
    private List<IoTDevice> devices;
    // Methods
    public Boolean isAvailable() {
        return this.status == RoomStatus.AVAILABLE;
    }

    public Double getPrice() {
        return this.basePrice;
    }


}