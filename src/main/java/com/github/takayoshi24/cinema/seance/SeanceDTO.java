package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;

import java.time.ZonedDateTime;
import java.util.UUID;

public record SeanceDTO(UUID id, Movie movie, ZonedDateTime startingAt, Integer room){

    public SeanceDTO(Seance seance){
        this(seance.getId(),seance.getMovie(),seance.getStartingAt(),seance.getRoom());
    }
}
