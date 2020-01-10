package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.UUID;

@Entity
public class Climber {

    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String firstName;
    @ManyToMany
    @JsonIgnore
    private List<CompetitionRound> compRounds;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID code;
    @Email
    private String email;

    public Climber() {
    }

    public Climber(String lastName, String firstName, List<CompetitionRound> compRounds, @Email String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.compRounds = compRounds;
        this.code = code;
        this.email = email;
    }

    public Climber(Long id, String lastName, String firstName, List<CompetitionRound> compRounds, @Email String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.compRounds = compRounds;
        this.code = code;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<CompetitionRound> getCompRounds() {
        return compRounds;
    }

    public void setCompRounds(List<CompetitionRound> compRounds) {
        this.compRounds = compRounds;
    }

    public UUID getCode() {
        return code;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
