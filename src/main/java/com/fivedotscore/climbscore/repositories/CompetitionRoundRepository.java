package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.CompetitionRound;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRoundRepository extends CrudRepository<CompetitionRound,Long> {


    Iterable<CompetitionRound> findCompetitionRoundsByCompetition_Id(Long id);
}
