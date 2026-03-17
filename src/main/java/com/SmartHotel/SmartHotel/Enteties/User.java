package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    // Getters & Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String passwordHash;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

}