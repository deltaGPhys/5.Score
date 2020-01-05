package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Route;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends CrudRepository<Route,Long> {

    Iterable<Route> findRoutesByZone_Id(Long zoneId);

}

