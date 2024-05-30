package com.EatEasy.Services;

import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Models.user.Role;
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


    public UserDetails create(SignUpRequest signupRequest, String profilePictureUrl, Role role) {
        return userDetailsRepository.save(
                new User(
                        signupRequest.getName(),
                        signupRequest.getEmail(),
                        passwordEncoder.encode(signupRequest.getPassword()),
                        profilePictureUrl,
                        role
                )
        );
    }

    public User save(User user) {
        return userDetailsRepository.save(user);
    }

    public List<User> findAll() {
        return userDetailsRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userDetailsRepository.deleteById(userId);
    }
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsRepository userRepository;

    public User getUserByToken(String token) {
        String email = jwtService.getUsernameFromToken(token);
        return userRepository.findByEmail(email);
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
            userToUpdate.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }
        if (updateRequest.getProfilePicture() != null) {
            userToUpdate.setProfilePicture(updateRequest.getProfilePicture());
        }

        return userDetailsRepository.save(userToUpdate);
    }
}
