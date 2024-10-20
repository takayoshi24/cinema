package com.github.takayoshi24.cinema.seance;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record SeanceUpdateDTO(@NotNull ZonedDateTime startingAt, @NotNull Integer room) {
}
