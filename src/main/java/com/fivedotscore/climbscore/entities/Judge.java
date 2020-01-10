package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fivedotscore.climbscore.serializers.ZoneDeserializer;
import com.fivedotscore.climbscore.serializers.ZoneSerializer;

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
    @JsonIgnore
    private CompetitionRound compRound;

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

    public CompetitionRound getCompRound() {
        return compRound;
    }

    public void setCompRound(CompetitionRound comp) {
        this.compRound = comp;
    }

}
