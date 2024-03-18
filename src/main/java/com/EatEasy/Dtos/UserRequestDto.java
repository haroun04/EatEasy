package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private List<Long> favoriteRestaurantIds;
    private List<Long> bookingIds;
    private List<Long> reviewIds;
}
