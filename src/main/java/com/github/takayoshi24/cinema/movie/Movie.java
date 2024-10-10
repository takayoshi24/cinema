package com.github.takayoshi24.cinema.movie;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

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
    private String type;

    public Movie(String title, ZonedDateTime validAt, String type) {
        this.title = title;
        this.validAt = validAt;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", validAt=" + validAt +
                ", type=" + type +
                '}';
    }

    public void updateReservation(ZonedDateTime validAt, String type) {
        this.validAt = validAt;
        this.type = type;
    }
}
