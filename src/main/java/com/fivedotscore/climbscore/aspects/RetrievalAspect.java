package com.fivedotscore.climbscore.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configuration
public class RetrievalAspect {

    Logger logger = LoggerFactory.getLogger(RetrievalAspect.class);

    @AfterThrowing("execution(* com.fivedotscore.climbscore.services.*.*(..))")
    public void logAfterThrowingAllMethods() throws Throwable {
        logger.info("Error caught: ");
    }

    @After("execution(* com.fivedotscore.climbscore.repositories.*.*(..))")
    public void logTest() {
        System.out.println("test");
    }
}
