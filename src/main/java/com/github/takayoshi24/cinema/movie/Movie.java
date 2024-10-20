package com.github.takayoshi24.cinema.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String title;
    @NotBlank
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public Movie(MovieCreateDTO dto){
        this.title = dto.title();
        this.genre = dto.genre();
    }
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                '}';
    }

    public void updateMovie(String genre, String title) {
        this.genre = genre;
        this.title = title;
    }

}
