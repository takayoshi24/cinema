package com.github.takayoshi24.cinema.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {


    Optional<Reservation> findFirstByEmail(String email);
    Page<Reservation> findAllByEmail(String email, Pageable pageable);
    List<Reservation> findAllByValidAtBetween(ZonedDateTime start, ZonedDateTime end);
    Optional<Reservation> findByTitleAndSeatPositionNumber(String title, Integer seatPositionNumber);
}
