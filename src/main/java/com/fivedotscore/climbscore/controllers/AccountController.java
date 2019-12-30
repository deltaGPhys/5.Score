package com.fivedotscore.climbscore.controllers;

import com.fivedotscore.climbscore.repositories.ClimberRepository;
import com.fivedotscore.climbscore.repositories.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins="http://localhost:8080")
public class AccountController {

    @Autowired
    ClimberRepository climberRepository;

    @Autowired
    JudgeRepository judgeRepository;
}
