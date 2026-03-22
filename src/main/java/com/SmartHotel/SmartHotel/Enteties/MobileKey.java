package com.SmartHotel.SmartHotel.Enteties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "mobile_keys")
public class MobileKey {

    // Getters & Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(unique = true, nullable = false)
    private String token;

    @Setter
    @Getter
    private Boolean isActive;
    private LocalDateTime expiresAt;
    private LocalDateTime lastUsedAt;

    // Methods
    public Boolean isValid() {
        return this.isActive &&
                this.expiresAt.isAfter(LocalDateTime.now());
    }

    public void revoke() {
        this.isActive = false;
    }

}

