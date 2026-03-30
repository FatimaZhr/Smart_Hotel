package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Getter
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    private String passwordHash;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

}