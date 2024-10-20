package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository <Reservation, UUID> {


    Optional<Reservation> findFirstByEmail(String email);
    Page<Reservation> findAllByEmail(String email, Pageable pageable);
    Optional<Reservation> findFirstBySeanceAndSeatPositionNumber(Seance seance, Integer seatPositionNumber);

}
