package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;

import java.util.UUID;

public record ReservationDTO(UUID id, String email, Seance seance, Integer seatPositionNumber) {
    public ReservationDTO(Reservation reservation){
        this(reservation.getId(),reservation.getEmail(),reservation.getSeance(),reservation.getSeatPositionNumber());
    }
}
