package com.SmartHotel.SmartHotel.Controllers;
import com.SmartHotel.SmartHotel.Enteties.MobileKey;
import com.SmartHotel.SmartHotel.Enteties.Reservation;
import com.SmartHotel.SmartHotel.services.MobileKeyService;
import com.SmartHotel.SmartHotel.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keys")
public class MobileKeyController {

    @Autowired
    MobileKeyService mobileKeyService;

    @Autowired
    ReservationService reservationService;

    // POST /api/keys/generate/1
    @PostMapping("/generate/{reservationId}")
    public MobileKey generateKey(
            @PathVariable Long reservationId) {
        Reservation reservation = reservationService
                .getReservationById(reservationId);
        return mobileKeyService.generateKey(reservation);
    }

    // GET /api/keys/validate?token=abc123
    @GetMapping("/validate")
    public Boolean validateKey(@RequestParam String token) {
        return mobileKeyService.validateKey(token);
    }

    // DELETE /api/keys/1/revoke
    @DeleteMapping("/{id}/revoke")
    public void revokeKey(@PathVariable Long id) {
        mobileKeyService.revokeKey(id);
    }
}