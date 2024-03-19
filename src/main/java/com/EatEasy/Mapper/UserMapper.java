package com.EatEasy.Mapper;
import com.EatEasy.Models.User;
import com.EatEasy.Dtos.UserRequestDto;
import com.EatEasy.Dtos.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserResponseDto> toResponseDtoList(List<User> users) {
        return users.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public User toModel(UserRequestDto userRequestDto) {
        return new User(
                null,
                null,
                userRequestDto.getName(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                null,
                null,
                null
        );
    }
    public User toModelfromRequestDto(Long userID) {
        return new User(
                userID,
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