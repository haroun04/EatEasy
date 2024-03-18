package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FavoriteRestaurantResponseDto {
    private final Long id;
    private Long userId;
    private Restaurant restaurantId;
}
