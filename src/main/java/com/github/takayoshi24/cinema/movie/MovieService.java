package com.github.takayoshi24.cinema.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getAllMovies(Pageable pageable){
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> findAllByGenre(String genre, Pageable pageable) {
        return movieRepository.findAllByGenre(genre,pageable);
    }

    public Movie addNewMovie(MovieCreateDTO movieData) {
        Optional<Movie> movieOptional =
                movieRepository.findFirstByTitle(movieData.title());
        if (movieOptional.isPresent()) {
            throw new IllegalStateException("Title: " + movieData.title() + " does exist");
        }
        Movie movie = new Movie(movieData);
        movieRepository.save(movie);
        return movie;
    }

    public void deleteMovie(UUID movieId) {
        boolean exits = movieRepository.existsById(movieId);
        if (!exits) {
            throw new IllegalStateException("Movie with id: " + movieId + " does not exits");
        }
        movieRepository.deleteById(movieId);
    }

    public Movie updateMovie(UUID movieId, String genre, String title) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalStateException(
                "Movie with id: " + movieId + " does not exits"));
            movie.updateMovie(genre, title);
            movieRepository.save(movie);
            return movie;
    }
}
