package com.fivedotscore.climbscore.services;

import com.fivedotscore.climbscore.repositories.ScoringSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoringSystemService {

    @Autowired
    ScoringSystemRepository scoringSystemRepository;


}
