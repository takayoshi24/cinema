package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seans.Seans;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void addNewReservation(Reservation reservation) {
        Optional<Reservation> reservationOptional =
                reservationRepository.findBySeansAndSeatPositionNumber(reservation.getSeans(), reservation.getSeatPositionNumber());
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("Seat nr.: " + reservation.getSeatPositionNumber() + " for title: " + reservation.getSeans().getMovie().getTitle());
        }
        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long reservationId) {
        boolean exits = reservationRepository.existsById(reservationId);
        if (!exits) {
            throw new IllegalStateException("Reservation with id: " + reservationId + " does not exits");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Transactional
    public void updateReservation(Long reservationId, Seans seans, Integer seatPositionNumber) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalStateException(
                "Reservation with id: " + reservationId + " does not exits"));
        if (seans != null && !Objects.equals(reservation.getSeans(), seans) &&
                seatPositionNumber != null && seatPositionNumber > 0 && !Objects.equals(reservation.getSeatPositionNumber(), seatPositionNumber)) {
             reservation.updateReservation(seans, seatPositionNumber);
            }
    }

}
