package com.fivedotscore.climbscore.services;

import com.fivedotscore.climbscore.aspects.RetrievalAspect;
import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.Gym;
import com.fivedotscore.climbscore.exceptions.ObjectNotFoundException;
import com.fivedotscore.climbscore.repositories.CompetitionRepository;
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
        return gym;
    }

    public Gym modifyGym(Gym gym) {
        Gym modifiedGym = null;
        try {
            modifiedGym = gymRepository.save(gym);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return gym;
    }

    public Iterable<Competition> findAllCompetitions() {

        Iterable<Competition> result = competitionRepository.findAll();
        return result;
    }

    public Competition findCompetitionById(Long id) {

        try {
            return competitionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        } catch (ObjectNotFoundException e) {
            //logger.error("Error: object not found");
        }
        return null;
    }

    public Competition addNewCompetition(Competition competition) {
        Competition createdCompetition = null;
        try {
            createdCompetition = competitionRepository.save(competition);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return competition;
    }

    public Competition modifyCompetition(Competition competition) {
        Competition modifiedCompetition = null;
        try {
            modifiedCompetition = competitionRepository.save(competition);
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
        }
        return competition;
    }
}
