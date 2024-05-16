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
    /*
    @PutMapping("/{email}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String email, @RequestBody UserRequestDto user) {
        User updatedUser = userService.updateUserInfo(email, user);
        if (updatedUser != null) {
            UserResponseDto userResponseDto = userMapper.toResponse(updatedUser);
            return ResponseEntity.ok(userResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("")
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


    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UserRequestDto updateRequest,
            @RequestHeader(name = "Authorization") String token
    ) {
        String authToken = token.substring(7);
        User updatedUser = userService.updateUserInfo(updateRequest, authToken);
        UserResponseDto responseDto = userMapper.toResponse(updatedUser);
        return ResponseEntity.ok(responseDto);
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
