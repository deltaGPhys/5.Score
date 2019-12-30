package com.fivedotscore.climbscore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private CompetitionRound compRound;
    @OneToMany
    private List<Route> routes;

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
