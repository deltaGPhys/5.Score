package com.fivedotscore.climbscore.authentication;

import com.fivedotscore.climbscore.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody RegistrationRequest registrationRequest) {
        return new ResponseEntity<User>(authService.signup(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        authService.login(loginRequest);
        AuthResponse response = new AuthResponse(authService.getCurrentUsername().orElse(null), authService.login(loginRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
