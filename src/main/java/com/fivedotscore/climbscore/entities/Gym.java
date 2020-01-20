package com.fivedotscore.climbscore.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comps=" + comps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return Objects.equals(id, gym.id) &&
                Objects.equals(name, gym.name) &&
                Objects.equals(comps, gym.comps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comps);
    }
}
