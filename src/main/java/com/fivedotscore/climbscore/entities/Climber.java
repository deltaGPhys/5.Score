package com.fivedotscore.climbscore.entities;

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
    private List<CompetitionRound> compRounds;
    private String code;
    @Email
    private String email;


}
