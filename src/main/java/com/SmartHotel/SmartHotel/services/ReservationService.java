package com.SmartHotel.SmartHotel.services;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import com.SmartHotel.SmartHotel.Enums.RoomStatus;
import com.SmartHotel.SmartHotel.Repositories.ReservationRepository;
import com.SmartHotel.SmartHotel.Repositories.RoomRepository;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Data
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;


    public Reservation createReservation(Reservation reservation) {

        Room room = reservation.getRoom();
        if (!room.isAvailable()) {
            throw new RuntimeException("Room is not available!");
        }

        room.setStatus(RoomStatus.OCCUPIED);
        roomRepository.save(room);
        long nights = ChronoUnit.DAYS.between(
                reservation.getCheckInDate(),
                reservation.getCheckOutDate()
        );
        if (nights <= 0) {
            throw new RuntimeException("Invalid dates!");
        }
        reservation.setTotalPrice(room.getBasePrice() * nights);

        room.setStatus(RoomStatus.OCCUPIED);
        roomRepository.save(room);

        reservation.setStatus(ReservStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    // check-in
    public Reservation checkIn(Long id) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));
        reservation.checkIn();
        return reservationRepository.save(reservation);
    }

    //  check-out
    public Reservation checkOut(Long id) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));
        reservation.checkOut();

        Room room = reservation.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);
        roomRepository.save(room);

        return reservationRepository.save(reservation);
    }


    public Reservation cancelReservation(Long id) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));
        reservation.cancel();
        Room room = reservation.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);
        roomRepository.save(room);

        return reservationRepository.save(reservation);
    }


    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByGuest(User loggedUser) {
        return  reservationRepository.findByGuest(loggedUser);
    }



}