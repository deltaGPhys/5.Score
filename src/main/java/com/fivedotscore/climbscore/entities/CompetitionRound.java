package com.fivedotscore.climbscore.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class CompetitionRound {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Competition comp;
    private LocalDate date;
    @OneToMany
    private List<Zone> zones;
    @ManyToMany
    private List<Climber> climbers;
    @OneToMany
    private List<Judge> judges;

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

    public Competition getComp() {
        return comp;
    }

    public void setComp(Competition comp) {
        this.comp = comp;
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
