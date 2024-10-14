package com.github.takayoshi24.cinema.seans;

import com.github.takayoshi24.cinema.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Optional;

public interface SeansRepository extends JpaRepository<Seans,Long> {

    Optional<Seans> findByMovieAndValidAtAndRoom(Movie movie, ZonedDateTime validAt, Integer room);

    Page<Seans> findAllByMovie(Movie movie, Pageable pageable);
}
