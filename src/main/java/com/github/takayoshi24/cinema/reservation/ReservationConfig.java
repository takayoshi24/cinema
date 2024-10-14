package com.github.takayoshi24.cinema.reservation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfig {

    /*@Bean
    CommandLineRunner commandLineRunner(ReservationRepository repository){
        return args -> {
            Reservation reservation = new Reservation(
                    "Harry Potter",
                    "poter@gmail.com",
                    ZonedDateTime.of(2025, 01, 01, 0, 0, 0, 0, ZoneId.of("UTC")),
                    6);

            repository.save(reservation);
        };
    }*/
}
