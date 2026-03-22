package com.SmartHotel.SmartHotel.services;
import com.SmartHotel.SmartHotel.Enteties.MobileKey;
import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.Repositories.MobileKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MobileKeyService {

    @Autowired
    MobileKeyRepository mobileKeyRepository;


    public MobileKey generateKey(Reservation reservation) {
        MobileKey key = new MobileKey();
        key.setReservation(reservation);
        key.setToken(UUID.randomUUID().toString());
        key.setIsActive(true);
        key.setExpiresAt(
                reservation.getCheckOutDate()
                        .atStartOfDay());
        return mobileKeyRepository.save(key);
    }

    public Boolean validateKey(String token) {
        MobileKey key = mobileKeyRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("invalid token!"));
        return key.isValid();
    }


    public void revokeKey(Long id) {
        MobileKey key = mobileKeyRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(" token not found!"));
        key.revoke();
        mobileKeyRepository.save(key);
    }
}
