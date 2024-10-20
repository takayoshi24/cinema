package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Page<Reservation> getReservationsByEmail(String email, Pageable pageable) {
        return reservationRepository.findAllByEmail(email, pageable);
    }

    public Reservation addNewReservation(ReservationCreateDTO reservationData) {
        Optional<Reservation> reservationOptional =
                reservationRepository.findFirstBySeanceAndSeatPositionNumber(reservationData.seance(), reservationData.seatPositionNumber());
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("Seat nr.: " + reservationData.seatPositionNumber() + " for title: " +reservationData.seance().getMovie().getTitle());
        }
       Reservation reservation = new Reservation(reservationData);
               reservationRepository.save(reservation);
        return reservation;
    }

    public void deleteReservation(UUID reservationId) {
        boolean exits = reservationRepository.existsById(reservationId);
        if (!exits) {
            throw new IllegalStateException("Reservation with id: " + reservationId + " does not exits");
        }
        reservationRepository.deleteById(reservationId);
    }

    public Reservation updateReservation(UUID reservationId, Seance seance, Integer seatPositionNumber) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalStateException(
                "Reservation with id: " + reservationId + " does not exits"));
        reservation.updateReservation(seance, seatPositionNumber);
        reservationRepository.save(reservation);
        return reservation;

    }

}
