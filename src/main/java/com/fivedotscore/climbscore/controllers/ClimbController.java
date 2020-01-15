package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.entities.Route;
import com.fivedotscore.climbscore.repositories.AttemptRepository;
import com.fivedotscore.climbscore.repositories.RouteRepository;
import com.fivedotscore.climbscore.repositories.ZoneRepository;
import com.fivedotscore.climbscore.services.ClimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class ClimbController {

    @Autowired
    ClimbService climbService;

    @GetMapping("/routes")
    public ResponseEntity<Iterable<Route>> getAllRoutes() {
        return new ResponseEntity<>(climbService.findAllRoutes(), HttpStatus.OK);
    }

}
