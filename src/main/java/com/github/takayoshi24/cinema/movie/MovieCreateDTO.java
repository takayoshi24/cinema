package com.github.takayoshi24.cinema.movie;

import jakarta.validation.constraints.NotBlank;

public record MovieCreateDTO(@NotBlank String title, @NotBlank String genre) {

}
