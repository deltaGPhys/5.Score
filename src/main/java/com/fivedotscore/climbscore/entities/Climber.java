package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fivedotscore.climbscore.serializers.CompetitionRoundDeserializer;
import com.fivedotscore.climbscore.serializers.CompetitionRoundSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.List;

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
    private String code;
    @Email
    private String email;

    public Climber() {
    }

    public Climber(String lastName, String firstName, List<CompetitionRound> compRounds, String code, @Email String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.compRounds = compRounds;
        this.code = code;
        this.email = email;
    }

    public Climber(Long id, String lastName, String firstName, List<CompetitionRound> compRounds, String code, @Email String email) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
