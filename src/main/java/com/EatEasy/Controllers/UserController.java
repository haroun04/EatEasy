package com.EatEasy.Controllers;


import com.EatEasy.Models.User;
import com.EatEasy.Services.UserService;
import com.EatEasy.Dtos.UserRequestDto;
import com.EatEasy.Dtos.UserResponseDto;
import com.EatEasy.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("getAllUsers");
        List<User> users = userService.findAll();
        List<UserResponseDto> responseDtoList = userMapper.toResponseDtoList(users);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        log.info("getById");
        User user = userService.findById(id);
        UserResponseDto responseDto = userMapper.toResponse(user);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto) {
        log.info("createUser");
        User user = userMapper.toModel(requestDto);
        User savedUser = userService.save(user);
        UserResponseDto responseDto = userMapper.toResponse(savedUser);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        log.info("updateuser");
        User user = userMapper.toModel(requestDto);
        User updatedUser = userService.update(id, user);
        if (updatedUser != null) {
            UserResponseDto responseDto = userMapper.toResponse(updatedUser);
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("getAllBookings");
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}