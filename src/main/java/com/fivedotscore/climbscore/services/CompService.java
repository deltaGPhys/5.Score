package com.fivedotscore.climbscore.services;

import com.fivedotscore.climbscore.aspects.RetrievalAspect;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.CompetitionRound;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.exceptions.ObjectNotFoundException;
import com.fivedotscore.climbscore.repositories.CompetitionRepository;
import com.fivedotscore.climbscore.repositories.CompetitionRoundRepository;
import com.fivedotscore.climbscore.repositories.GymRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompService {

    @Autowired
    GymRepository gymRepository;

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    CompetitionRoundRepository competitionRoundRepository;

    Logger logger = LoggerFactory.getLogger(RetrievalAspect.class);

    public Iterable<Gym> findAllGyms() {
        Iterable<Gym> result = gymRepository.findAll();
        return result;
    }

    public Gym findGymById(Long id) {
        Gym gym = null;
        try {
            gym = gymRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        } catch (ObjectNotFoundException e) {
            logger.error("Error: object not found");
        }
        return gym;
    }

    public Gym addNewGym(Gym gym) {
        Gym createdGym = null;
        try {
            createdGym = gymRepository.save(gym);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return createdGym;
    }

    public Gym modifyGym(Gym gym) {
        Gym modifiedGym = null;
        try {
            modifiedGym = gymRepository.save(gym);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return modifiedGym;
    }

    public Iterable<Competition> findAllCompetitions() {
        Iterable<Competition> result = competitionRepository.findAll();
        return result;
    }

    public Iterable<Competition> findAllCompetitionsForGym(Long id) {
        Iterable<Competition> result = competitionRepository.findCompetitionsByGym_Id(id);
        System.out.println(result);
        return result;
    }

    public Competition findCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public Competition addNewCompetition(Competition competition) {
        Competition createdCompetition = null;
        try {
            createdCompetition = competitionRepository.save(competition);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return createdCompetition;
    }

    public Competition modifyCompetition(Competition competition) {
        Competition modifiedCompetition = null;
        try {
            modifiedCompetition = competitionRepository.save(competition);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return modifiedCompetition;
    }

    public boolean verifyRound(Long roundId) {
        return competitionRoundRepository.existsById(roundId);
    }

    public Iterable<CompetitionRound> findAllCompetitionRounds() {
        Iterable<CompetitionRound> result = competitionRoundRepository.findAll();
        return result;
    }

    public Iterable<CompetitionRound> findAllCompetitionRoundsForCompetition(Long id) {
        Iterable<CompetitionRound> result = competitionRoundRepository.findCompetitionRoundsByCompetition_Id(id);
        System.out.println(result);
        return result;
    }

    public CompetitionRound findCompetitionRoundById(Long id) {
        return competitionRoundRepository.findById(id).orElse(null);
    }

    public CompetitionRound addNewCompetitionRound(CompetitionRound competitionRound) {
        CompetitionRound createdCompetitionRound = null;
        try {
            createdCompetitionRound = competitionRoundRepository.save(competitionRound);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return createdCompetitionRound;
    }

    public CompetitionRound modifyCompetitionRound(CompetitionRound competitionRound) {
        CompetitionRound modifiedCompetitionRound = null;
        try {
            modifiedCompetitionRound = competitionRoundRepository.save(competitionRound);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return modifiedCompetitionRound;
    }

}
