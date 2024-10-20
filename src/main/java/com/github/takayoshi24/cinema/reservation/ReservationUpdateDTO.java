package com.github.takayoshi24.cinema.reservation;

import jakarta.validation.constraints.NotNull;


public record ReservationUpdateDTO(@NotNull Integer seatPositionNumber) {
}
