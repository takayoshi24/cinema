package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record SeanceCreateDTO(@NotNull Movie movie, @NotNull ZonedDateTime startingAt, @NotNull Integer room) {

}
