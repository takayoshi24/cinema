package com.github.takayoshi24.cinema.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationDTO registerNewReservation(@Valid @RequestBody ReservationCreateDTO reservationData){
        Reservation reservation = reservationService.addNewReservation(reservationData);
        return new ReservationDTO(reservation);
    }

    @GetMapping(params = {"email"})
    public Page<Reservation> getReservationsByEmail(@RequestParam String  email, Pageable pageable){
        return reservationService.getReservationsByEmail(email, pageable);
    }

    @GetMapping
    public List<Reservation> getReservations(){
        return reservationService.getReservations();
    }


    @PutMapping(path = "/{reservationId}")
    public ReservationDTO updateReservation(
            @PathVariable("reservationId") UUID reservationId,
            @RequestBody ReservationCreateDTO reservationData){
       Reservation reservation = reservationService.updateReservation(reservationId, reservationData.seance(), reservationData.seatPositionNumber());
       return new ReservationDTO(reservation);
    }

    @DeleteMapping(path = "{reservationId}")
    public String deleteReservation(@PathVariable("reservationId") UUID reservationId){
        reservationService.deleteReservation(reservationId);
        return "Reservation has been deleted";
    }


}
