package com.fivedotscore.climbscore.services;

import com.fivedotscore.climbscore.aspects.RetrievalAspect;
import com.fivedotscore.climbscore.entities.*;
import com.fivedotscore.climbscore.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoundService {
    Logger logger = LoggerFactory.getLogger(RetrievalAspect.class);

    @Autowired
    ZoneRepository zoneRepository;
    
    @Autowired
    RouteRepository routeRepository;

    @Autowired
    CompetitionRoundRepository competitionRoundRepository;

    @Autowired
    ClimberRepository climberRepository;

    @Autowired
    JudgeRepository judgeRepository;

    @Autowired
    CompService compService;

    @Autowired
    RoundService roundService;

    public boolean verifyZone(Long zoneId) {
        return zoneRepository.existsById(zoneId);
    }

    public Iterable<Zone> findAllZones() {
        return zoneRepository.findAll();
    }

    public Zone findZoneById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    public Iterable<Zone> findAllZonesForRound(Long roundId) {
        return zoneRepository.findZonesByCompRound_Id(roundId);
    }


    public boolean verifyRound(Long roundId) {
        return competitionRoundRepository.existsById(roundId);
    }

    public Iterable<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    public Route findRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Iterable<Route> findAllRoutesForZone(Long zoneId) {
        return routeRepository.findRoutesByZone_Id(zoneId);
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

    public Route addNewRoute(Route route) {
        Route newRoute = null;

        try {
            newRoute = routeRepository.save(route);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        return newRoute;
    }


    public Iterable<Climber> findAllClimbers() {
        return climberRepository.findAll();
    }

    public Climber findClimberById(Long id) {
        return climberRepository.findById(id).orElse(null);
    }

    public Iterable<Climber> findAllClimbersForRound(Long roundId) {
        return climberRepository.findClimbersByCompRounds(compService.findCompetitionRoundById(roundId));
    }

    public boolean verifyJudge(Long judgeId) {
        return judgeRepository.existsById(judgeId);
    }

    public Iterable<Judge> findAllJudges() {
        return judgeRepository.findAll();
    }

    public Judge findJudgeById(Long id) {
        return judgeRepository.findById(id).orElse(null);
    }

    public Iterable<Judge> findAllJudgesForRound(Long roundId) {
        Iterable<Judge> judges = judgeRepository.findJudgesByCompRound(compService.findCompetitionRoundById(roundId));
        return judgeRepository.findJudgesByCompRound(compService.findCompetitionRoundById(roundId));
    }

    public CompetitionRound findRoundForJudge(Long judgeId) {
        Judge judge = findJudgeById(judgeId);
        return judge.getCompRound();
    }
}
