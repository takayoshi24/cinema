package com.github.takayoshi24.cinema.movie;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Movie> findAllByGenre(String genre, Pageable pageable) {
        return movieRepository.findAllByGenre(genre,pageable);
    }

    public void addNewMovie(Movie movie) {
        Optional<Movie> movieOptional =
                movieRepository.findByTitleAndValidAt(movie.getTitle(), movie.getValidAt());
        if (!movieOptional.isPresent()) {
            throw new IllegalStateException("Title: " + movie.getTitle() + " for day: " + movie.getValidAt() + " does exist");
        }
        movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        boolean exits = movieRepository.existsById(movieId);
        if (!exits) {
            throw new IllegalStateException("Reservation with id: " + movieId + " does not exits");
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(Long movieId, ZonedDateTime validAt, String genre) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException(
                "Movie with id: " + movieId + " does not exits"));
        if (validAt != null && !Objects.equals(movie.getValidAt(), validAt) &&
                genre != null && !Objects.equals(movie.getGenre(), genre)) {
            movie.updateReservation(validAt, genre);
        }
    }
}
