package com.github.takayoshi24.cinema.seans;

import com.github.takayoshi24.cinema.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeansService {

    private final SeansRepository seansRepository;

    public SeansService(SeansRepository seansRepository) {
        this.seansRepository = seansRepository;
    }

    public Page<Seans> findAllByMovie(Movie movie, Pageable pageable) {
        return seansRepository.findAllByMovie(movie,pageable);
    }

    public void addNewSeans(Seans seans) {
        Optional<Seans> seansOptional =
                seansRepository.findByMovieAndValidAtAndRoom(seans.getMovie(), seans.getValidAt(),seans.getRoom());
        if (!seansOptional.isPresent()) {
            throw new IllegalStateException("Movie: " + seans.getMovie().getTitle()+ " for day: "
                    + seans.getValidAt() + " and for room: "+ seans.getRoom() +" does exist");
        }
        seansRepository.save(seans);
    }

    public void deleteSeans(Long seansId) {
        boolean exits = seansRepository.existsById(seansId);
        if (!exits) {
            throw new IllegalStateException("Seans with id: " + seansId + " does not exits");
        }
        seansRepository.deleteById(seansId);
    }

    public void updateSeans(Long seansId, ZonedDateTime validAt, Integer room) {
        Seans seans = seansRepository.findById(seansId).orElseThrow(() -> new IllegalStateException(
                "Seans with id: " + seansId + " does not exits"));
        if (validAt != null && !Objects.equals(seans.getValidAt(), validAt) &&
                room != null && !Objects.equals(seans.getRoom(), room)) {
            seans.updateSeans(validAt, room);
        }
    }
}
