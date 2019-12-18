package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoringSystemRepository extends CrudRepository<ScoringSystem,Long> {

}
