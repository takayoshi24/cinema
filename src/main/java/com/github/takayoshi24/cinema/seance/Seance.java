package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Table (uniqueConstraints = { @UniqueConstraint(columnNames = { "starting_at", "room" }) })
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @NotNull
    private Movie movie;
    @Column(name = "starting_at")
    @NotNull
    private ZonedDateTime startingAt;
    @NotNull
    private Integer room;

    public Seance(Movie movie, ZonedDateTime startingAt, Integer room) {
        this.movie = movie;
        this.startingAt = startingAt;
        this.room = room;
    }

    public Seance(SeanceCreateDTO dto){
        this.movie = dto.movie();
        this.startingAt = dto.startingAt();
        this.room = dto.room();
    }

    public void updateSeance(ZonedDateTime validAt, Integer room ){
        this.startingAt = validAt;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", movie=" + movie +
                ", startingAt=" + startingAt +
                ", room=" + room +
                '}';
    }
}
