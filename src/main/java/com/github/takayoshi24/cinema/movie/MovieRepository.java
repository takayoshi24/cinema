package com.github.takayoshi24.cinema.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    Optional<Movie> findFirstByGenre(String genre);
    Page<Movie> findAllByGenre(String genre, Pageable pageable);
    Optional<Movie> findFirstByTitle(String title);

}
