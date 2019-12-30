package com.fivedotscore.climbscore.dtos;

import com.fivedotscore.climbscore.entities.Competition;
import com.fivedotscore.climbscore.entities.CompetitionRound;
import com.fivedotscore.climbscore.entities.Gym;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionDTO {


    private Long id;

    private Long gymId;
    private String name;
    private List<Long> roundIds;

    public CompetitionDTO() {
    }

    public CompetitionDTO(Competition competition) {
        this.id = competition.getId();
        this.gymId = competition.getGym().getId();
        this.name = competition.getName();
        if (competition.getRounds() != null) {
            this.roundIds = competition.getRounds().stream().mapToLong(round -> round.getId()).boxed().collect(Collectors.toList());
        } else {
            this.roundIds = Arrays.asList(new Long[0]);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getRoundIds() {
        return roundIds;
    }

    public void setRoundIds(List<Long> roundIds) {
        this.roundIds = roundIds;
    }
}
