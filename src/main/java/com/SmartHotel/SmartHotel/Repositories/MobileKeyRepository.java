package com.SmartHotel.SmartHotel.Repositories;

import com.SmartHotel.SmartHotel.Enteties.MobileKey;
import com.SmartHotel.SmartHotel.Enteties.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobileKeyRepository
        extends JpaRepository<MobileKey, Long> {

    Optional<MobileKey> findByToken(String token);

    Optional<MobileKey> findByReservation(Reservation reservation);

    List<MobileKey> findByIsActiveTrue();
}
