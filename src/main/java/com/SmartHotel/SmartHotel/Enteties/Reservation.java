package com.SmartHotel.SmartHotel.Enteties;

import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {

    // Getters & Setters
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private ReservStatus status;

    private Double totalPrice;

    // Methods
    public void confirm() {
        this.status = ReservStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = ReservStatus.CANCELLED;
    }

    public void checkIn() {
        this.status = ReservStatus.CHECKED_IN;
    }

    public void checkOut() {
        this.status = ReservStatus.CHECKED_OUT;
    }

}