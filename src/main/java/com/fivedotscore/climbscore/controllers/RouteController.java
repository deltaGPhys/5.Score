package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.repositories.RouteRepository;
import com.fivedotscore.climbscore.repositories.ScoringSystemRepository;
import com.fivedotscore.climbscore.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class RouteController {

    @Autowired
    ZoneRepository zoneRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    ScoringSystemRepository scoringSystemRepository;


}
