package com.github.takayoshi24.cinema.seance;

import com.github.takayoshi24.cinema.movie.Movie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seances")
public class SeanceController {

    private final SeanceService seanceService;

    @PostMapping
    public SeanceDTO registerNewSeance(@Valid @RequestBody SeanceCreateDTO seanceData){
        Seance seance = seanceService.addNewSeance(seanceData);
        return new SeanceDTO(seance);
    }

    @GetMapping
    public Page<SeanceDTO> getAllSeances(Pageable pageable){
        Page<Seance> seance = seanceService.getAllSeance(pageable);
        return seance.map(SeanceDTO::new);
    }

    @GetMapping(params = {"seance"})
    public Page<Seance> getSeanceByMovie(@RequestParam Movie movie, Pageable pageable){
        return seanceService.findAllByMovie(movie, pageable);
    }

    @PutMapping(path = "{seanceId}")
    public SeanceDTO updateSeance(
            @PathVariable("seanceId") UUID seanceId,
            @RequestBody SeanceCreateDTO seanceData){
        Seance seance = seanceService.updateSeance(seanceId, seanceData.startingAt(), seanceData.room());
        return new SeanceDTO(seance);
    }
    @DeleteMapping(path = "{seanceId}")
    public String deleteSeance(@PathVariable("seanceId") UUID seanceId){
        seanceService.deleteSeance(seanceId);
        return "Seance has been deleted";
    }

}

