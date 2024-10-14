package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seans.Seans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {


    Optional<Reservation> findFirstByEmail(String email);
    Page<Reservation> findAllByEmail(String email, Pageable pageable);
    Optional<Reservation> findBySeansAndSeatPositionNumber(Seans seans, Integer seatPositionNumber);

}
