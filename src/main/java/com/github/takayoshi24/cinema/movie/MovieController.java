package com.github.takayoshi24.cinema.movie;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;


    @PostMapping
    public MovieDTO registerNewMovie(@Valid @RequestBody MovieCreateDTO movieData){
        Movie movie = movieService.addNewMovie(movieData);
        return new MovieDTO(movie);
    }

    @GetMapping
    public Page<MovieDTO> getAllMovies(Pageable pageable){
        Page<Movie> movies = movieService.getAllMovies(pageable);
        return movies.map(MovieDTO::new);
    }


    @GetMapping(params = {"genre"})
    public Page<Movie> getMovieByGenre(@RequestParam String  genre, Pageable pageable){
        return movieService.findAllByGenre(genre,pageable);
    }


    @PutMapping(path = "/{movieId}")
    public MovieDTO updateMovie(
            @PathVariable("movieId") UUID movieId,
            @RequestBody MovieCreateDTO movieData){
        Movie movie = movieService.updateMovie(movieId, movieData.genre(), movieData.title());
        return new MovieDTO(movie);
    }

    @DeleteMapping(path = "/{movieId}")
    public String deleteMovie(@PathVariable("movieId") UUID movieId){
        movieService.deleteMovie(movieId);
        return "Movie has been deleted";
    }
}
