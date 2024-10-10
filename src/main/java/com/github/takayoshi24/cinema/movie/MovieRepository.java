package com.github.takayoshi24.cinema.movie;

import com.github.takayoshi24.cinema.reservation.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> findFirstByType(String type);
    Page<Movie> findAllByType(String type, Pageable pageable);
    Optional<Movie> findByTitleAndValidAt(String title, ZonedDateTime validAt);

}
