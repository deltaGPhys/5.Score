package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.ScoringSystem;
import com.fivedotscore.climbscore.entities.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository<Zone,Long> {

}

