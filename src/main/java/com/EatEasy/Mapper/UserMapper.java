package com.EatEasy.Mapper;


import com.EatEasy.Dtos.UserDto.UserRequestDto;
import com.EatEasy.Dtos.UserDto.UserResponseDto;
import com.EatEasy.Models.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toResponse(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getProfilePicture(),
                user.getEmail(),
                user.getPassword(),
                user.getFavoriteRestaurants(),
               user.getBookings(),
                user.getReviews()
        );
    }

    public User toModel(UserRequestDto userDTO) {
        return new User(
                null,
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getProfilePicture(),
            null,
                null,
                null
        );
    }

    public User toModelFromRequestDto(Long userID) {
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
