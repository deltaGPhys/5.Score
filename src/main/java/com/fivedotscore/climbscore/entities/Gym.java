package com.fivedotscore.climbscore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Gym {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<Competition> comps;

    public Gym(String name, List<Competition> comps) {
        this.name = name;
        this.comps = comps;
    }

    public Gym(Long id, String name, List<Competition> comps) {
        this.id = id;
        this.name = name;
        this.comps = comps;
    }

    public Gym() {
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

    public List<Competition> getComps() {
        return comps;
    }

    public void setComps(List<Competition> comps) {
        this.comps = comps;
    }
}
