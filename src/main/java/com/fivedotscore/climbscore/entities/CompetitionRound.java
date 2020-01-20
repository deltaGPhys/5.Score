package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fivedotscore.climbscore.serializers.CompetitionDeserializer;
import com.fivedotscore.climbscore.serializers.CompetitionSerializer;
import com.fivedotscore.climbscore.serializers.ZoneDeserializer;
import com.fivedotscore.climbscore.serializers.ZoneSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class CompetitionRound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JsonSerialize(using = CompetitionSerializer.class)
    @JsonDeserialize(using = CompetitionDeserializer.class)
    private Competition competition;
    private LocalDate date;
    @OneToMany
    @JsonSerialize(using = ZoneSerializer.class)
    @JsonDeserialize(using = ZoneDeserializer.class)
    private List<Zone> zones;
    @ManyToMany
    private List<Climber> climbers;
    @OneToMany
    private List<Judge> judges;

    public CompetitionRound() {
    }

    public CompetitionRound(String name, Competition competition, LocalDate date, List<Zone> zones, List<Climber> climbers, List<Judge> judges) {
        this.name = name;
        this.competition = competition;
        this.date = date;
        this.zones = zones;
        this.climbers = climbers;
        this.judges = judges;
    }

    public CompetitionRound(Long id, String name, Competition competition, LocalDate date, List<Zone> zones, List<Climber> climbers, List<Judge> judges) {
        this.id = id;
        this.name = name;
        this.competition = competition;
        this.date = date;
        this.zones = zones;
        this.climbers = climbers;
        this.judges = judges;
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

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition comp) {
        this.competition = comp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }

    public List<Climber> getClimbers() {
        return climbers;
    }

    public void setClimbers(List<Climber> climbers) {
        this.climbers = climbers;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    }
}
