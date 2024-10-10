package com.github.takayoshi24.cinema.movie;

import com.github.takayoshi24.cinema.reservation.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.ZonedDateTime;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(params = {"type"})
    public Page<Movie> getMovieByType(@RequestParam String  type, Pageable pageable){
        return movieService.findMovieByType(type,pageable);
    }

    @PostMapping
    public void registerNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }

    @DeleteMapping(path = "{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId){
        movieService.deleteMovie(movieId);
    }

    @PutMapping(path = "{movieId}")
    public void updateMovie(
            @PathVariable("movieId") Long movieId,
            @RequestParam(required = false) ZonedDateTime validAt,
            @RequestParam(required = false) String type){
        movieService.updateMovie(movieId, validAt, type);
    }
}
