package com.fivedotscore.climbscore;

public enum ClimbResult {
    FLASH("Flash"),
    SEND("Send"),
    FALL("Fall"),
    NA("N/A")
    ;


    private String name;

    ClimbResult(String name) {
        this.name = name;
    }
}
