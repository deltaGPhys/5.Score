package com.fivedotscore.climbscore.entities;

import com.fivedotscore.climbscore.ClimbResult;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Attempt {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Route route;
    private Double score;
    private ClimbResult result;
    @ManyToOne
    private Judge judge;
    private LocalDateTime timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ClimbResult getResult() {
        return result;
    }

    public void setResult(ClimbResult result) {
        this.result = result;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
