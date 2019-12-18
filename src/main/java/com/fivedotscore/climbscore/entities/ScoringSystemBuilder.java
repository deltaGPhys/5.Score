package com.fivedotscore.climbscore.entities;

import java.util.HashMap;
import java.util.TreeMap;

public class ScoringSystemBuilder {

    private Long id;
    private String name;
    private TreeMap<Double, String> scoreVals;

    public ScoringSystemBuilder(Long id) {
        this.scoreVals = new TreeMap<>();
        this.id = id;
    }

    public ScoringSystemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ScoringSystemBuilder addLevel(Double value, String name) {
        this.scoreVals.put(value,name);
        return this;
    }

    public ScoringSystemBuilder addLevel(Double value) {
        this.scoreVals.put(value,String.valueOf(value));
        return this;
    }

    public ScoringSystem build() {
        return new ScoringSystem(id, name, scoreVals);
    }
}
