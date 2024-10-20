package com.github.takayoshi24.cinema.reservation;

import com.github.takayoshi24.cinema.seance.Seance;
import com.github.takayoshi24.cinema.seance.SeanceRepository;
import com.github.takayoshi24.cinema.seance.SeanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SeanceService seanceService;

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Page<Reservation> getReservationsByEmail(String email, Pageable pageable) {
        return reservationRepository.findAllByEmail(email, pageable);
    }

    public Reservation addNewReservation(ReservationCreateDTO reservationData) {
        Optional<Reservation> reservationOptional =
                reservationRepository.findFirstBySeanceIdAndSeatPositionNumber(reservationData.seanceId(), reservationData.seatPositionNumber());
        Seance seance = seanceService.getSeanceById(reservationData.seanceId()).orElseThrow( () ->
                new IllegalStateException("Movie with id: " + reservationData.seanceId() + " does not exits")
        );
        if (reservationOptional.isPresent()) {
            throw new IllegalStateException("Seat nr.: " + reservationData.seatPositionNumber() +
                    " for title: " +seance.getMovie().getTitle() + "does exist");
        }
       Reservation reservation = new Reservation(reservationData,seance);
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

    public Reservation updateReservation(UUID reservationId, Integer seatPositionNumber) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new IllegalStateException(
                "Reservation with id: " + reservationId + " does not exits"));
        reservation.updateReservation(seatPositionNumber);
        reservationRepository.save(reservation);
        return reservation;

    }

}
