package com.github.takayoshi24.cinema.movie;

import java.util.UUID;

public record MovieDTO (UUID id, String title, String genre){

    public MovieDTO (Movie movie){
        this(movie.getId(), movie.getTitle(), movie.getGenre());
    }
}
