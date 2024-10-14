package com.github.takayoshi24.cinema.seans;

import com.github.takayoshi24.cinema.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Getter
@Table (uniqueConstraints = { @UniqueConstraint(columnNames = { "movie_id", "room" }) })
@NoArgsConstructor
public class Seans {
    @Id
    @SequenceGenerator(name = "seans_sequence",
            sequenceName = "seans_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "seans_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private ZonedDateTime validAt;
    private Integer room;

    public Seans(Movie movie, ZonedDateTime validAt, Integer room) {
        this.movie = movie;
        this.validAt = validAt;
        this.room = room;
    }

    public void updateSeans(ZonedDateTime validAt, Integer room ){
        this.validAt = validAt;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Seans{" +
                "id=" + id +
                ", movie=" + movie +
                ", validAt=" + validAt +
                ", room=" + room +
                '}';
    }
}
