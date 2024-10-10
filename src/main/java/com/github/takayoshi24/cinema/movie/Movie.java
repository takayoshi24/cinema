package com.github.takayoshi24.cinema.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

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
    private ZonedDateTime validAt;
    private String genre;

    public Movie(String title, ZonedDateTime validAt, String genre) {
        this.title = title;
        this.validAt = validAt;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", validAt=" + validAt +
                ", genre=" + genre +
                '}';
    }

    public void updateReservation(ZonedDateTime validAt, String genre) {
        this.validAt = validAt;
        this.genre = genre;
    }
}
