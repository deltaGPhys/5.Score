package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.dtos.CompetitionDTO;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.repositories.*;
import com.fivedotscore.climbscore.services.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class CompController {

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    CompetitionRoundRepository competitionRoundRepository;

    @Autowired
    CompService compService;

    @GetMapping("/gyms")
    public ResponseEntity<Iterable<Gym>> getAllGyms() {
        return new ResponseEntity<>(compService.findAllGyms(), HttpStatus.OK);
    }

    @GetMapping("/gyms/{id}")
    public ResponseEntity<Gym> getGymById(@PathVariable Long id) {
        return new ResponseEntity<Gym>(compService.findGymById(id), HttpStatus.OK);
    }

    @PostMapping("/gyms")
    public ResponseEntity<Gym> addNewGym(@RequestBody Gym gym) {
        return new ResponseEntity<Gym>(compService.addNewGym(gym), HttpStatus.CREATED);
    }

    @PutMapping("/gyms/{id}")
    public ResponseEntity<Gym> modifyGym(@PathVariable Long id, @RequestBody Gym gym) {
        return new ResponseEntity<Gym>(compService.modifyGym(gym), HttpStatus.OK);
    }

    @GetMapping("/competitions")
    public ResponseEntity<Iterable<CompetitionDTO>> getAllCompetitions() {
        Iterable<Competition> comp = compService.findAllCompetitions();
        Iterable<CompetitionDTO> results = StreamSupport.stream(comp.spliterator(),false).map(c -> new CompetitionDTO(c)).collect(Collectors.toList());
        return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/competitions/{id}")
    public ResponseEntity<CompetitionDTO> getCompetitionById(@PathVariable Long id) {
        Competition newCompetition = compService.findCompetitionById(id);
        return new ResponseEntity((newCompetition != null)?new CompetitionDTO(newCompetition):null, HttpStatus.OK);
    }

    @PostMapping("/competitions")
    public ResponseEntity<CompetitionDTO> addNewCompetition(@RequestBody Competition competition) {
        return new ResponseEntity(new CompetitionDTO(compService.addNewCompetition(competition)), HttpStatus.CREATED);
    }

    @PutMapping("/competitions/{id}")
    public ResponseEntity<CompetitionDTO> modifyCompetition(@PathVariable Long id, @RequestBody Competition competition) {
        return new ResponseEntity(new CompetitionDTO(compService.modifyCompetition(competition)), HttpStatus.OK);
    }


}
