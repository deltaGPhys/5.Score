package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.dtos.CompetitionRoundDTO;
import com.fivedotscore.climbscore.entities.*;
import com.fivedotscore.climbscore.repositories.RouteRepository;
import com.fivedotscore.climbscore.repositories.ScoringSystemRepository;
import com.fivedotscore.climbscore.repositories.ZoneRepository;
import com.fivedotscore.climbscore.services.CompService;
import com.fivedotscore.climbscore.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class RoundController {

    @Autowired
    RoundService roundService;

    @Autowired
    CompService compService;

    @GetMapping("/zones")
    public ResponseEntity<Iterable<Zone>> getAllZones() {
        return new ResponseEntity<>(roundService.findAllZones(), HttpStatus.OK);
    }

    @GetMapping("/zones/round/{roundId}")
    public ResponseEntity<Iterable<Zone>> getAllZonesForRound(@PathVariable Long roundId) {
        return (compService.verifyRound(roundId)) ? new ResponseEntity<>(roundService.findAllZonesForRound(roundId), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/zones/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable Long id) {
        return new ResponseEntity<>(roundService.findZoneById(id), HttpStatus.OK);
    }


    @GetMapping("/routes")
    public ResponseEntity<Iterable<Route>> getAllRoutes() {
        return new ResponseEntity<>(roundService.findAllRoutes(), HttpStatus.OK);
    }

    @GetMapping("/routes/round/{zoneId}")
    public ResponseEntity<Iterable<Route>> getAllRoutesForZone(@PathVariable Long zoneId) {
        return (roundService.verifyZone(zoneId)) ? new ResponseEntity<>(roundService.findAllRoutesForZone(zoneId), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        return new ResponseEntity<>(roundService.findRouteById(id), HttpStatus.OK);
    }


    @GetMapping("/climbers")
    public ResponseEntity<Iterable<Climber>> getAllClimbers() {
        return new ResponseEntity<>(roundService.findAllClimbers(), HttpStatus.OK);
    }

    @GetMapping("/climbers/round/{roundId}")
    public ResponseEntity<Iterable<Climber>> getAllClimbersForRound(@PathVariable Long roundId) {
        return (roundService.verifyRound(roundId)) ? new ResponseEntity<>(roundService.findAllClimbersForRound(roundId), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/climbers/{id}")
    public ResponseEntity<Climber> getClimberById(@PathVariable Long id) {
        return new ResponseEntity<>(roundService.findClimberById(id), HttpStatus.OK);
    }


    @GetMapping("/judges")
    public ResponseEntity<Iterable<Judge>> getAllJudges() {
        return new ResponseEntity<>(roundService.findAllJudges(), HttpStatus.OK);
    }

    @GetMapping("/judges/round/{roundId}")
    public ResponseEntity<Iterable<Judge>> getAllJudgesForRound(@PathVariable Long roundId) {
        return (roundService.verifyRound(roundId)) ? new ResponseEntity<>(roundService.findAllJudgesForRound(roundId), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/judges/single/{judgeId}")
    public ResponseEntity<CompetitionRoundDTO> getRoundForJudge(@PathVariable Long judgeId) {
        return (roundService.verifyJudge(judgeId)) ? new ResponseEntity<>(new CompetitionRoundDTO(roundService.findRoundForJudge(judgeId)), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/judges/{id}")
    public ResponseEntity<Judge> getJudgeById(@PathVariable Long id) {
        return new ResponseEntity<>(roundService.findJudgeById(id), HttpStatus.OK);
    }
}
