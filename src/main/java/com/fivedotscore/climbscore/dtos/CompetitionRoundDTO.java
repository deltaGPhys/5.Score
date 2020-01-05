package com.fivedotscore.climbscore.dtos;

import com.fivedotscore.climbscore.entities.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionRoundDTO {

    private Long id;
    private String name;
    private Long competitionId;
    private LocalDate date;
    private List<Long> zoneIds;
    private List<Long> climberIds;
    private List<Long> judgeIds;

    public CompetitionRoundDTO() {
    }

    public CompetitionRoundDTO(CompetitionRound competitionRound) {
        this.id = competitionRound.getId();
        this.name = competitionRound.getName();
        this.competitionId = competitionRound.getCompetition().getId();
        this.date = competitionRound.getDate();
        if (competitionRound.getZones() != null) {
            this.zoneIds = competitionRound.getZones().stream().mapToLong(zone -> zone.getId()).boxed().collect(Collectors.toList());
        } else {
            this.zoneIds = Arrays.asList(new Long[0]);
        }
        if (competitionRound.getClimbers() != null) {
            this.climberIds = competitionRound.getClimbers().stream().mapToLong(climber -> climber.getId()).boxed().collect(Collectors.toList());
        } else {
            this.climberIds = Arrays.asList(new Long[0]);
        }
        if (competitionRound.getJudges() != null) {
            this.judgeIds = competitionRound.getJudges().stream().mapToLong(judge -> judge.getId()).boxed().collect(Collectors.toList());
        } else {
            this.judgeIds = Arrays.asList(new Long[0]);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Long> getZoneIds() {
        return zoneIds;
    }

    public void setZoneIds(List<Long> zoneIds) {
        this.zoneIds = zoneIds;
    }

    public List<Long> getClimberIds() {
        return climberIds;
    }

    public void setClimberIds(List<Long> climberIds) {
        this.climberIds = climberIds;
    }

    public List<Long> getJudgeIds() {
        return judgeIds;
    }

    public void setJudgeIds(List<Long> judgeIds) {
        this.judgeIds = judgeIds;
    }

    @Override
    public String toString() {
        return "CompetitionRoundDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", competitionId=" + competitionId +
                ", date=" + date +
                ", zoneIds=" + zoneIds +
                ", climberIds=" + climberIds +
                ", judgeIds=" + judgeIds +
                '}';
    }
}
