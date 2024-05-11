package com.EatEasy.Controllers;


import com.EatEasy.Models.user.User;
import com.EatEasy.Services.UserDetailServiceImpl;
import com.EatEasy.auth.JWTService;
import com.EatEasy.auth.LogInRequest;
import com.EatEasy.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.EatEasy.Services.InitialDataCreationService.userProfilePicture;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class authcontroller {
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map> login(@RequestBody LogInRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                ));
        return ResponseEntity.ok(Map.of("token",
                jwtService.createToken(authentication.getName()
                ))
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetails> signup(@RequestBody SignUpRequest signupRequest) {
        String profilePictureUrl = userProfilePicture();
        return ResponseEntity.ok(
                userDetailsService.create(signupRequest, profilePictureUrl)
        );
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDetailsService.findAll());
    }
}