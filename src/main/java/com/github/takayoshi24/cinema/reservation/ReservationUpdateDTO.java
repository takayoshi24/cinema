package com.github.takayoshi24.cinema.reservation;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationUpdateDTO(@NotNull UUID reservationId, @NotNull Integer seatPositionNumber) {
}
