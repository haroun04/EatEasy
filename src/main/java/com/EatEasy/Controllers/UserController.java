package com.EatEasy.Controllers;


import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Dtos.UserDto.UserResponseDto;
import com.EatEasy.Mapper.UserMapper;
import com.EatEasy.Models.Review;
import com.EatEasy.Models.user.User;
import com.EatEasy.Services.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")                      // PERMITE EL INTERCAMBIO ENTRE BACKEND Y FRONTEND PUERTO DE ANGULAR
@RequiredArgsConstructor
public class UserController {
    private final UserDetailServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserByToken(@RequestHeader(name = "Authorization") String token) {
        String authToken = token.substring(7);
        User user = userService.getUserByToken(authToken);
        UserResponseDto userResponseDto = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponseDto);
    }

    /*@GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserResponseDto> userResponseDtos = userMapper.toResponse(users);
        return ResponseEntity.ok(userResponseDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponseDto userResponseDto = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponseDto);
    }
*/


    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUser(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.loadUserByUsername(email)));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> postUser(
            @RequestBody UserRequestDto user
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.save(userMapper.toModel(user))));
    }



    /*
    * @PutMapping("/{email}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto user
    ) {
        return ResponseEntity.ok(userMapper.toResponse(userService.updateUser(id, userMapper.toModel(user))));
    }
    *
    *
    * @PutMapping("/{id}")
    public User updateUser(Long id, User updatedUser) {
        User user = userDetailsRepository.findById(id).orElse(null);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setProfilePicture(updatedUser.getProfilePicture());
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    * */



}
