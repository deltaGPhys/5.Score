package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends CrudRepository<Gym,Long> {

}

