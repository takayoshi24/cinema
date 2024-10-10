package com.github.takayoshi24.cinema.reservation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(params = {"email"})
    public Page<Reservation> getReservationsByEmail(@RequestParam String  email, Pageable pageable){
        return reservationService.getReservationsByEmail(email,pageable);
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping
    public void registerNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
    }

    @DeleteMapping(path = "{reservationId}")
    public void deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
    }

    @PutMapping(path = "{reservationId}")
    public void updateReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestParam(required = false) ZonedDateTime validAt,
            @RequestParam(required = false) Integer seatPositionNumber){
        reservationService.updateReservation(reservationId, validAt, seatPositionNumber);
    }


}
