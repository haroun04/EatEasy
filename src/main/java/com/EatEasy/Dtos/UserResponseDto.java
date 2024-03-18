package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private List<Long> favoriteRestaurantIds;
    private List<Long> bookingIds;
    private List<Long> reviewIds;
}
