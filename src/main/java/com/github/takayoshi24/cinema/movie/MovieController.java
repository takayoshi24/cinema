package com.github.takayoshi24.cinema.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(params = {"genre"})
    public Page<Movie> getMovieByGenre(@RequestParam String  genre, Pageable pageable){
        return movieService.findAllByGenre(genre,pageable);
    }

    @PostMapping(path="/movie")
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
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String title){
        movieService.updateMovie(movieId, genre, title);
    }
}
