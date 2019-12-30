package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition,Long> {

}
