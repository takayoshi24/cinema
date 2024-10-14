package com.github.takayoshi24.cinema.seans;

import com.github.takayoshi24.cinema.movie.Movie;
import com.github.takayoshi24.cinema.reservation.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class SeansController {

    private final SeansService seansService;

    public SeansController(SeansService seansService) {
        this.seansService = seansService;
    }

    @GetMapping(params = {"movie"})
    public Page<Seans> getSeansByMovie(@RequestParam Movie movie, Pageable pageable){
        return seansService.findAllByMovie(movie, pageable);
    }


    @PostMapping(path="/seans")
    public void registerNewSeans(@RequestBody Seans seans){
        seansService.addNewSeans(seans);
    }

    @DeleteMapping(path = "{seansId}")
    public void deleteSeans(@PathVariable("seansId") Long seansId){
        seansService.deleteSeans(seansId);
    }

    @PutMapping(path = "{seansId}")
    public void updateSenas(
            @PathVariable("seansId") Long seansId,
            @RequestParam(required = false) ZonedDateTime validAt,
            @RequestParam(required = false) Integer room){
        seansService.updateSeans(seansId, validAt, room);
    }
}

