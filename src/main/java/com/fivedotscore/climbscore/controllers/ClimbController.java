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
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class ClimbController {

    @Autowired
    ClimbService climbService;

//    @GetMapping("/routes")
//    public ResponseEntity<Iterable<Route>> getAllRoutes() {
//        return new ResponseEntity<>(climbService.findAllRoutes(), HttpStatus.OK);
//    }
//
//    @GetMapping("/routes/{id}")
//    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
//        return new ResponseEntity<>(climbService.findRouteById(id), HttpStatus.OK);
//    }
//
//    @GetMapping("/routes/zone/{id}")
//    public ResponseEntity<Iterable<Route>> getRouteByZone(@PathVariable Long zoneId) {
//        return new ResponseEntity<>(climbService.findRoutesByZone(zoneId), HttpStatus.OK);
//    }
//
//    @PostMapping("/routes")
//    public ResponseEntity<Route> addNewRoute(@RequestBody Route route) {
//        return new ResponseEntity<>(climbService.addNewRoute(route), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/routes/{id}")
//    public ResponseEntity<Route> modifyRoute(@PathVariable Long id, @RequestBody Route route) {
//        return new ResponseEntity<>(climbService.modifyRoute(route), HttpStatus.OK);
//    }

}
