package com.github.takayoshi24.cinema.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence")
    private Long id;
    private String title;
    private String genre;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
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
