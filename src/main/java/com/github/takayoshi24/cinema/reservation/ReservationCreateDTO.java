package com.github.takayoshi24.cinema.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationCreateDTO(@NotBlank String email,@NotNull UUID seanceId, @NotNull Integer seatPositionNumber) {
}
