package com.fivedotscore.climbscore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Judge {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    @ManyToOne
    private Competition comp;
    @OneToMany
    private List<Zone> zones;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Competition getComp() {
        return comp;
    }

    public void setComp(Competition comp) {
        this.comp = comp;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
}
