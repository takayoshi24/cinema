package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

public interface SeanceRepository extends JpaRepository<Seance, UUID> {

    Optional<Seance> findByMovieIdAndStartingAtAndRoom(UUID movieId, ZonedDateTime validAt, Integer room);

    Page<Seance> findAllByMovie(Movie movie, Pageable pageable);
}
