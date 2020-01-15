package com.fivedotscore.climbscore.services;

import com.fivedotscore.climbscore.aspects.RetrievalAspect;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.entities.Route;
import com.fivedotscore.climbscore.exceptions.ObjectNotFoundException;
import com.fivedotscore.climbscore.repositories.AttemptRepository;
import com.fivedotscore.climbscore.repositories.RouteRepository;
import com.fivedotscore.climbscore.repositories.ScoringSystemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClimbService {

    Logger logger = LoggerFactory.getLogger(RetrievalAspect.class);
    
    @Autowired
    RouteRepository routeRepository;
    
    @Autowired
    ScoringSystemRepository scoringSystemRepository;
    
    @Autowired
    ScoringSystemService scoringSystemService;
    
    @Autowired
    AttemptRepository attemptRepository;

    @Autowired
    RoundService roundService;

    public Iterable<Route> findAllRoutes() {
        Iterable<Route> result = routeRepository.findAll();
        return result;
    }

    public Iterable<Route> findRoutesByZone(Long zoneId) {
        if (roundService.verifyZone(zoneId)) {
            return routeRepository.findRoutesByZone_Id(zoneId);
        } else {
            return null;
        }
    }

    public Route findRouteById(Long id) {
        Route route = null;
        try {
            route = routeRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        } catch (ObjectNotFoundException e) {
            logger.error("Error: object not found");
        }
        return route;
    }

    public Route addNewRoute(Route route) {
        Route createdRoute = null;
        try {
            createdRoute = routeRepository.save(route);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return createdRoute;
    }

    public Route modifyRoute(Route route) {
        Route modifiedRoute = null;
        try {
            modifiedRoute = routeRepository.save(route);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return modifiedRoute;
    }
}
