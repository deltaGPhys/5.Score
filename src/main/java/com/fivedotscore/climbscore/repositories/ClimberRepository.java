package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Climber;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimberRepository extends CrudRepository<Climber,Long> {

}
