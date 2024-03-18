package com.EatEasy.Mapper;
import com.EatEasy.Models.User;
import com.EatEasy.Dtos.UserRequestDto;
import com.EatEasy.Dtos.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getFavoriteRestaurants(),
                user.getBookings(),
                user.getReviews()
        );
    }

    public User toModel(UserRequestDto userRequestDto) {
        return new User(
                0L, // Asigna un valor válido para la base de datos
                UUID.randomUUID(),
                userRequestDto.getName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                null, // Dejar las listas como nulas por ahora
                null,
                null
        );
    }


    public User toModelfromRequestDto(Long userId) {
        return new User(
                userId,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}