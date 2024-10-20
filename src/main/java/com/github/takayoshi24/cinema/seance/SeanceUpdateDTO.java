package com.github.takayoshi24.cinema.seance;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public record SeanceUpdateDTO(@NotNull UUID seanceId, @NotNull ZonedDateTime startingAt, @NotNull Integer room) {
}
