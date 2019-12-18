package com.fivedotscore.climbscore.entities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class ScoringSystem {

    @Id
    private Long id;
    private String name;
//    private String scoreValJson;
//    @Transient
    @Column(length=100000)
    private TreeMap<Double, String> scoreVals;

    public ScoringSystem(Long id, String name, TreeMap<Double, String> scoreVals) {
        this.id = id;
        this.name = name;
//        this.scoreValJson = scoreValJson;
//        this.scoreVals = jsonToMap(this.scoreValJson);
        this.scoreVals = scoreVals;
    }

//    public ScoringSystem(Long id, String name, TreeMap<Double, String> scoreVals) {
//        this.id = id;
//        this.name = name;
//        this.scoreVals = scoreVals;
//        this.scoreValJson = mapToJson(this.scoreVals);
//    }

    public ScoringSystem() {
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

    public TreeMap<Double, String> getScoreVals() {
        return scoreVals;
    }

    public String mapToJson(TreeMap<Double, String> map) {
        return new Gson().toJson(map);
    }

    public TreeMap<Double, String> jsonToMap(String json) {
        Type mapType = new TypeToken<Map<String, Double>>() {}.getType();
        return new Gson().fromJson(json,mapType);
    }

    public static String hasher(ScoringSystem system) {
        String hash = system.getName();
        for (Map.Entry<Double, String> entry : system.getScoreVals().entrySet()) {
            hash += String.valueOf(entry.getKey()) + entry.getValue();
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        return hasher(this).equals(hasher((ScoringSystem) other));
    }

    public String toString() {
        String string = String.valueOf(this.id) + "/" + this.name + ": ";
        for (Map.Entry<Double, String> entry : this.scoreVals.entrySet()) {
            string += "(" + String.valueOf(entry.getKey()) + ":" + entry.getValue() + ") ";
        }
        return string;
    }
}
