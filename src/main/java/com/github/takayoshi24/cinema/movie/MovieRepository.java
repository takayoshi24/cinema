package com.github.takayoshi24.cinema.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    Optional<Movie> findFirstByGenre(String genre);
    Page<Movie> findAllByGenre(String genre, Pageable pageable);
    Optional<Movie> findByTitle(String title);

}
