package com.fivedotscore.climbscore.authentication;

import com.fivedotscore.climbscore.authentication.RegistrationRequest;
import com.fivedotscore.climbscore.entities.Judge;
import com.fivedotscore.climbscore.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<User>(authService.signup(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
