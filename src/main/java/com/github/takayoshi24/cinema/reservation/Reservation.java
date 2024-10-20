package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table (uniqueConstraints = { @UniqueConstraint(columnNames = { "seance_id", "seatPositionNumber" }) })
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String email;
    @ManyToOne
    @NotNull
    private Seance seance;
    @NotNull
    private Integer seatPositionNumber;

    public Reservation(String email, Seance seance, Integer seatPositionNumber) {
        this.email = email;
        this.seance = seance;
        this.seatPositionNumber = seatPositionNumber;
    }

    public Reservation(ReservationCreateDTO dto, Seance seance){
        this.email = dto.email();
        this.seatPositionNumber = dto.seatPositionNumber();
        this.seance = seance;
    }
    public void updateReservation(Integer seatPositionNumber ){
        this.seatPositionNumber = seatPositionNumber;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", seans=" + seance +
                ", seatPositionNumber=" + seatPositionNumber +
                '}';
    }
}
