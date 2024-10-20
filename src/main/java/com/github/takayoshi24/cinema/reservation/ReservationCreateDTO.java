package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationCreateDTO(@NotBlank String email, @NotNull Seance seance, @NotNull Integer seatPositionNumber) {
}
