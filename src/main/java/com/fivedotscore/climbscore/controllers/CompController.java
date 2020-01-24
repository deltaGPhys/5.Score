package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.dtos.CompetitionDTO;
import com.fivedotscore.climbscore.dtos.CompetitionRoundDTO;
import com.fivedotscore.climbscore.entities.*;
import com.fivedotscore.climbscore.repositories.*;
import com.fivedotscore.climbscore.services.CompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@CrossOrigin(origins="*", allowedHeaders = "Authorization")
public class CompController {

    @Autowired
    CompService compService;

    @GetMapping("/gyms")
    public ResponseEntity<Iterable<Gym>> getAllGyms() {
        return new ResponseEntity<>(compService.findAllGyms(), HttpStatus.OK);
    }

    @GetMapping("/gyms/{id}")
    public ResponseEntity<Gym> getGymById(@PathVariable Long id) {
        return new ResponseEntity<>(compService.findGymById(id), HttpStatus.OK);
    }

    @PostMapping("/gyms")
    public ResponseEntity<Gym> addNewGym(@RequestBody Gym gym) {
        return new ResponseEntity<>(compService.addNewGym(gym), HttpStatus.CREATED);
    }

    @PutMapping("/gyms/{id}")
    public ResponseEntity<Gym> modifyGym(@PathVariable Long id, @RequestBody Gym gym) {
        return new ResponseEntity<>(compService.modifyGym(gym), HttpStatus.OK);
    }

    @GetMapping("/competitions")
    public ResponseEntity<Iterable<CompetitionDTO>> getAllCompetitions() {
        Iterable<Competition> comp = compService.findAllCompetitions();
        Iterable<CompetitionDTO> results = StreamSupport.stream(comp.spliterator(),false).map(CompetitionDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/competitions/gym/{id}")
    public ResponseEntity<Iterable<CompetitionDTO>> getAllCompetitionsForGym(@PathVariable Long id) {
        Iterable<Competition> comp = compService.findAllCompetitionsForGym(id);
        Iterable<CompetitionDTO> results = StreamSupport.stream(comp.spliterator(),false).map(CompetitionDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/competitions/{id}")
    public ResponseEntity<CompetitionDTO> getCompetitionById(@PathVariable Long id) {
        Competition newCompetition = compService.findCompetitionById(id);
        return new ResponseEntity<>((newCompetition != null) ? new CompetitionDTO(newCompetition) : null, HttpStatus.OK);
    }

    @PostMapping("/competitions")
    public ResponseEntity<CompetitionDTO> addNewCompetition(@RequestParam Long gymId, @RequestBody Competition competition) {
        competition.setGym(compService.findGymById(gymId));
        return new ResponseEntity<>(new CompetitionDTO(compService.addNewCompetition(competition)), HttpStatus.CREATED);
    }

    @PutMapping("/competitions/{id}")
    public ResponseEntity<CompetitionDTO> modifyCompetition(@PathVariable Long id, @RequestBody Competition competition) {
        return new ResponseEntity<>(new CompetitionDTO(compService.modifyCompetition(competition)), HttpStatus.OK);
    }

    @GetMapping("/competitionRounds")
    public ResponseEntity<Iterable<CompetitionRoundDTO>> getAllCompetitionRounds() {
        Iterable<CompetitionRound> comp = compService.findAllCompetitionRounds();
        Iterable<CompetitionRoundDTO> results = StreamSupport.stream(comp.spliterator(),false).map(CompetitionRoundDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/competitionRounds/competition/{id}")
    public ResponseEntity<Iterable<CompetitionRoundDTO>> getAllCompetitionRoundsForCompetition(@PathVariable Long id) {
        Iterable<CompetitionRound> comp = compService.findAllCompetitionRoundsForCompetition(id);
        Iterable<CompetitionRoundDTO> results = StreamSupport.stream(comp.spliterator(),false).map(CompetitionRoundDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/competitionRounds/{id}")
    public ResponseEntity<CompetitionRoundDTO> getCompetitionRoundById(@PathVariable Long id) {
        CompetitionRound newCompetitionRound = compService.findCompetitionRoundById(id);
        return new ResponseEntity<>((newCompetitionRound != null) ? new CompetitionRoundDTO(newCompetitionRound) : null, HttpStatus.OK);
    }

    @PostMapping("/competitionRounds")
    public ResponseEntity<CompetitionRoundDTO> addNewCompetitionRound(@RequestParam Long compId, @RequestBody CompetitionRound competitionRound) {
        competitionRound.setCompetition(compService.findCompetitionById(compId));
        competitionRound.setClimbers(new ArrayList<Climber>());
        competitionRound.setZones(new ArrayList<Zone>());
        competitionRound.setJudges(new ArrayList<Judge>());
        return new ResponseEntity<>(new CompetitionRoundDTO(compService.addNewCompetitionRound(competitionRound)), HttpStatus.CREATED);
    }

    @PutMapping("/competitionRounds/{id}")
    public ResponseEntity<CompetitionRoundDTO> modifyCompetitionRound(@PathVariable Long id, @RequestBody CompetitionRound competitionRound) {
        return new ResponseEntity<>(new CompetitionRoundDTO(compService.modifyCompetitionRound(competitionRound)), HttpStatus.OK);
    }

}
