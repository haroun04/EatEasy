package com.EatEasy.Services;


import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Models.user.User;
import com.EatEasy.Repository.UserDetailsRepository;
import com.EatEasy.auth.JWTService;
import com.EatEasy.auth.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*
    *  public User updateUser(Long id, User user) {
        User userToUpdate = userDetailsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        // Actualiza los campos solo si han sido proporcionados y son diferentes de null
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        // Continúa con otros campos según sea necesario

        return userDetailsRepository.save(userToUpdate);
        *
    }
    * public User findById(Long id) {
        return userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }*/


    public List<User> findAll() {
        return userDetailsRepository.findAll();
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

    public User updateUserInfo(UserRequestDto updateRequest, String token) {
        String email = jwtService.getUsernameFromToken(token);

        User userToUpdate = userDetailsRepository.findByEmail(email);

        if (updateRequest.getName() != null) {
            userToUpdate.setName(updateRequest.getName());
        }
        if (updateRequest.getEmail() != null) {
            userToUpdate.setEmail(updateRequest.getEmail());
        }
        if (updateRequest.getPassword() != null) {
            userToUpdate.setPassword(updateRequest.getPassword());
        }
        if (updateRequest.getProfilePicture() != null) {
            userToUpdate.setProfilePicture(updateRequest.getProfilePicture());
        }

        // Guardar y retornar el usuario actualizado
        return userDetailsRepository.save(userToUpdate);
    }
}

