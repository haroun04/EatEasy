package com.EatEasy.Dtos.UserDto;

import com.EatEasy.Models.user.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
public class UserRequestDto {
    private final String name;
    private final String email;
    private final String password;
    private final String profilePicture;
    private final Role role;
}
