package com.EatEasy.Mapper;
import com.EatEasy.Models.User;
import com.EatEasy.Dtos.UserRequestDto;
import com.EatEasy.Dtos.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
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
        return users.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public User toModel(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        return user;
    }
}