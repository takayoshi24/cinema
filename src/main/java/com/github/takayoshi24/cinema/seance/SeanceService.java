package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SeanceService {

    private final SeanceRepository seanceRepository;

    public Page<Seance> getAllSeance(Pageable pageable){
        return seanceRepository.findAll(pageable);
    }

    public Page<Seance> findAllByMovie(Movie movie, Pageable pageable) {
        return seanceRepository.findAllByMovie(movie,pageable);
    }

    public Seance addNewSeance(SeanceCreateDTO seanceData) {
        Optional<Seance> seanceOptional =
                seanceRepository.findByMovieAndStartingAtAndRoom(seanceData.movie(), seanceData.startingAt(), seanceData.room());
        if (seanceOptional.isPresent()) {
            throw new IllegalStateException("Movie: " + seanceData.movie().getTitle()+ " for day: "
                    + seanceData.startingAt() + " and for room: "+ seanceData.room() +" does not exist");
        }
        Seance seance = new Seance(seanceData);
        seanceRepository.save(seance);
        return seance;
    }

    public void deleteSeance(UUID seanceId) {
        boolean exits = seanceRepository.existsById(seanceId);
        if (!exits) {
            throw new IllegalStateException("Seance with id: " + seanceId + " does not exits");
        }
        seanceRepository.deleteById(seanceId);
    }

    public Seance updateSeance(UUID seanceId, ZonedDateTime validAt, Integer room) {
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(() -> new IllegalStateException(
                "Seance with id: " + seanceId + " does not exits"));
        seance.updateSeance(validAt, room);
        seanceRepository.save(seance);
        return seance;

    }
}
