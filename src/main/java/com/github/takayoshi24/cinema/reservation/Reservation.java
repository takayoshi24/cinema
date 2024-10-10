package com.github.takayoshi24.cinema.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence")
    private Long id;
    private String title;
    private String email;
    private ZonedDateTime validAt;
    private Integer seatPositionNumber;

    public void updateReservation(ZonedDateTime validAt, Integer seatPositionNumber ){
        this.validAt = validAt;
        this.seatPositionNumber = seatPositionNumber;
    }

    public Reservation(String title, String email, ZonedDateTime dob, Integer seatPositionNumber) {
        this.title = title;
        this.email = email;
        this.validAt = dob;
        this.seatPositionNumber = seatPositionNumber;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", validAt=" + validAt +
                ", seatPositionNumber=" + seatPositionNumber +
                '}';
    }
}
