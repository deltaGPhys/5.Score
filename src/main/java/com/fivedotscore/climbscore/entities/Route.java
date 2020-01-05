package com.fivedotscore.climbscore.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fivedotscore.climbscore.serializers.CompetitionRoundDeserializer;
import com.fivedotscore.climbscore.serializers.CompetitionRoundSerializer;
import com.fivedotscore.climbscore.serializers.ZoneDeserializer;
import com.fivedotscore.climbscore.serializers.ZoneSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Route {

    @Id
    @GeneratedValue
    private Long id;
    private String identifier;
    private String name;
    private Integer value;
    @ManyToOne
    private ScoringSystem system;
    @ManyToOne
    @JsonSerialize(using = ZoneSerializer.class)
    @JsonDeserialize(using = ZoneDeserializer.class)
    private Zone zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ScoringSystem getSystem() {
        return system;
    }

    public void setSystem(ScoringSystem system) {
        this.system = system;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
