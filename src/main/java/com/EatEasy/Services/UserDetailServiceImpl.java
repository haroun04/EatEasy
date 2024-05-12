package com.EatEasy.Services;


import com.EatEasy.Models.user.User;
import com.EatEasy.Repository.UserDetailsRepository;
import com.EatEasy.auth.JWTService;
import com.EatEasy.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDetailsRepository.findByEmail(email);
    }

    public UserDetails create(SignUpRequest signupRequest, String profilePictureUrl) {
        return userDetailsRepository.save(
                new User(
                        signupRequest.getName(),
                        signupRequest.getEmail(),
                        passwordEncoder.encode(signupRequest.getPassword()),
                        profilePictureUrl
                )
        );
    }

    public User save(User user) {
        return userDetailsRepository.save(user);
    }

    public User updateUser(String email, User user) {
        User userUpdated = this.loadUserByUsername(email);
        userUpdated.setName(user.getName());
        userDetailsRepository.save(userUpdated);
        return userUpdated;
    }

    public List<User> findAll() {
        return userDetailsRepository.findAll();
    }

    public User findById(Long id) {
        return userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsRepository userRepository;

    public User getUserByToken(String token) {
        // Verificar y decodificar el token JWT para obtener el nombre de usuario
        String email = jwtService.getUsernameFromToken(token);

        // Buscar el usuario en la base de datos utilizando el nombre de usuario
        User user = userRepository.findByEmail(email);

        // Retornar el usuario encontrado
        return user;
    }
}
