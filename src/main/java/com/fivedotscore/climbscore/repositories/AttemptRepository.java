package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Attempt;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends CrudRepository<Attempt,Long> {

}
