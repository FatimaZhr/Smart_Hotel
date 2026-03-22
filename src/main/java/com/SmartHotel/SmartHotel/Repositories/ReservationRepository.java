package com.SmartHotel.SmartHotel.Repositories;

import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Enteties.Room;
import com.SmartHotel.SmartHotel.Enteties.User;
import com.SmartHotel.SmartHotel.Enums.ReservStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuest(User guest);

    List<Reservation> findByRoom(Room room);

    List<Reservation> findByStatus(ReservStatus status);
}
