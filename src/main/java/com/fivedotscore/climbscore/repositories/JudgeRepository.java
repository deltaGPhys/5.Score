package com.fivedotscore.climbscore.repositories;

import com.fivedotscore.climbscore.entities.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeRepository extends CrudRepository<Judge,Long> {

    Iterable<Judge> findJudgesByCompRound(CompetitionRound round);
}
