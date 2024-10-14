package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seans.Seans;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table (uniqueConstraints = { @UniqueConstraint(columnNames = { "seans_id", "seatPositionNumber" }) })
@NoArgsConstructor
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence")
    private Long id;
    private String email;
    @ManyToOne
    @JoinColumn(name = "seans_id")
    private Seans seans;
    private Integer seatPositionNumber;

    public void updateReservation(Seans seans, Integer seatPositionNumber ){
        this.seans = seans;
        this.seatPositionNumber = seatPositionNumber;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", seans=" + seans +
                ", seatPositionNumber=" + seatPositionNumber +
                '}';
    }
}
