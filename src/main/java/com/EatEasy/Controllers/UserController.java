package com.EatEasy.Controllers;

import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Dtos.UserDto.UserResponseDto;
import com.EatEasy.Mapper.UserMapper;
import com.EatEasy.Models.user.User;
import com.EatEasy.Services.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserDetailServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserByToken(@RequestHeader(name = "Authorization") String token) {
        String authToken = token.substring(7);
        User user = userService.getUserByToken(authToken);
        UserResponseDto userResponseDto = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponseDto);
    }

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

    @PatchMapping("/me/profile-picture")
    public ResponseEntity<UserResponseDto> updateProfilePicture(
            @RequestParam("file") MultipartFile file,
            @RequestHeader(name = "Authorization") String token
    ) {
        String authToken = token.substring(7);
        User user = userService.getUserByToken(authToken);

        if (file != null && !file.isEmpty()) {
            try {
                String folder = "assets/img/";
                Path path = Paths.get(folder + file.getOriginalFilename());
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                user.setProfilePicture(path.toString());
                userService.save(user);
                UserResponseDto responseDto = userMapper.toResponse(user);
                return ResponseEntity.ok(responseDto);
            } catch (IOException e) {
                log.error("Error saving file", e);
                return ResponseEntity.status(500).body(null);
            }
        } else {
            log.error("File is empty or null");
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        log.info("deleteUser");
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
