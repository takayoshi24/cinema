package com.github.takayoshi24.cinema.movie;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                movieRepository.findByTitle(movie.getTitle());
        if (!movieOptional.isPresent()) {
            throw new IllegalStateException("Title: " + movie.getTitle() + " does exist");
        }
        movieRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        boolean exits = movieRepository.existsById(movieId);
        if (!exits) {
            throw new IllegalStateException("Movie with id: " + movieId + " does not exits");
        }
        movieRepository.deleteById(movieId);
    }

    @Transactional
    public void updateMovie(Long movieId, String genre, String title) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException(
                "Movie with id: " + movieId + " does not exits"));
        if (title != null && !Objects.equals(movie.getTitle(), title) &&
                genre != null && !Objects.equals(movie.getGenre(), genre)) {
            movie.updateMovie(genre, title);
        }
    }
}
