package com.EatEasy.Controllers;

import com.EatEasy.Models.user.Role;
import com.EatEasy.Models.user.User;
import com.EatEasy.Services.UserDetailServiceImpl;
import com.EatEasy.auth.JWTService;
import com.EatEasy.auth.LogInRequest;
import com.EatEasy.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @PostMapping("/login/admin")
    public ResponseEntity<Map> loginAdmin(@RequestBody LogInRequest loginRequest) {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getName(),
                    loginRequest.getPassword()
            ));


            // Verificar si el usuario tiene el rol de ADMIN
            if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                // Generar token JWT para el usuario autenticado
                return ResponseEntity.ok(Map.of("token",
                        jwtService.createToken(authentication.getName()
                        ))
                );
            } else {
                // Si el usuario no es administrador, retornar un error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Access denied"));
            }
    }



    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody SignUpRequest signupRequest) {
        try {
            // Crear nuevo usuario con el rol de USER por defecto
            String profilePictureUrl = userProfilePicture();
            userDetailsService.create(signupRequest, profilePictureUrl, Role.USER);

            // Autenticar al nuevo usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signupRequest.getEmail(),
                            signupRequest.getPassword()
                    )
            );

            return ResponseEntity.ok(Map.of("token",
                    jwtService.createToken(authentication.getName()
                    ))
            );

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error during signup"));
        }
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<Map<String, String>> signupAdmin(@RequestBody SignUpRequest signupRequest) {
        try {
            // Crear nuevo usuario con el rol de ADMIN
            String profilePictureUrl = userProfilePicture();
            userDetailsService.create(signupRequest, profilePictureUrl, Role.ADMIN);

            // Autenticar al nuevo usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signupRequest.getEmail(),
                            signupRequest.getPassword()
                    )
            );

            return ResponseEntity.ok(Map.of("token",
                    jwtService.createToken(authentication.getName()
                    ))
            );

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error during signup"));
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDetailsService.findAll());
    }
}
