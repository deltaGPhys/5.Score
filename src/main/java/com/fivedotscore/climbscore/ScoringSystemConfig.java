package com.fivedotscore.climbscore;

import com.fivedotscore.climbscore.entities.ScoringSystem;
import com.fivedotscore.climbscore.entities.ScoringSystemBuilder;
import com.fivedotscore.climbscore.repositories.ScoringSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;


@Configuration
public class ScoringSystemConfig {

    @Autowired
    ScoringSystemRepository scoringSystemRepository;

    @Bean
    @PostConstruct
    public void sendFall () {
        ScoringSystemBuilder builder = new ScoringSystemBuilder(0L);
        builder.setName("Send/Fall");
        builder.addLevel(0.,"Fall");
        builder.addLevel(1.,"Send");
        ScoringSystem system = builder.build();

        scoringSystemRepository.save(system);
    }

    @Bean
    @PostConstruct
    public void holdNumber () {
        ScoringSystemBuilder builder = new ScoringSystemBuilder(1L);
        builder.setName("Hold Count");
        for (double i = 0.; i <= 5.; i+=.5) {
            builder.addLevel(i,String.valueOf(i));
        }
        ScoringSystem system = builder.build();

        scoringSystemRepository.save(system);
    }

    @PostConstruct
    public void showSystems () {
        for (ScoringSystem s : scoringSystemRepository.findAll()) {
            System.out.println(s.toString());
        }
    }
}
