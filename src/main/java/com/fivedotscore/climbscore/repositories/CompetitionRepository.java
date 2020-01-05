package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.ScoringSystem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition,Long> {

    @Query("SELECT c FROM Competition c WHERE c.gym.id = :id")
    public Iterable<Competition> findCompetitionsByGym_Id(@Param("id") Long id);

}
