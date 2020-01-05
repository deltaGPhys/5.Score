package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fivedotscore.climbscore.serializers.CompetitionRoundDeserializer;
import com.fivedotscore.climbscore.serializers.CompetitionRoundSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JsonSerialize(using = CompetitionRoundSerializer.class)
    @JsonDeserialize(using = CompetitionRoundDeserializer.class)
    private CompetitionRound compRound;
    @OneToMany
    private List<Route> routes;

    public Zone(Long id, String name, CompetitionRound compRound, List<Route> routes) {
        this.id = id;
        this.name = name;
        this.compRound = compRound;
        this.routes = routes;
    }

    public Zone(String name, CompetitionRound compRound, List<Route> routes) {
        this.name = name;
        this.compRound = compRound;
        this.routes = routes;
    }

    public Zone() {
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

    public CompetitionRound getCompRound() {
        return compRound;
    }

    public void setCompRound(CompetitionRound compRound) {
        this.compRound = compRound;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
