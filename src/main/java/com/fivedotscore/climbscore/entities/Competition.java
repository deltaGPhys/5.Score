package com.fivedotscore.climbscore.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Competition {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Gym gym;
    private String name;
    @OneToMany
    private List<CompetitionRound> rounds;

    public Competition() {
    }

    public Competition(Long id, Gym gym, String name, List<CompetitionRound> rounds) {
        this.id = id;
        this.gym = gym;
        this.name = name;
        this.rounds = rounds;
    }

    public Competition(Gym gym, String name, List<CompetitionRound> rounds) {
        this.gym = gym;
        this.name = name;
        this.rounds = rounds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompetitionRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<CompetitionRound> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", gym=" + gym +
                ", name='" + name + '\'' +
                ", rounds=" + rounds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gym, that.gym) &&
                Objects.equals(name, that.name) &&
                Objects.equals(rounds, that.rounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gym, name, rounds);
    }
}
