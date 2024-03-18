package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRestaurantResponseDto {
    private Long id;
    private Long userId;
    private Long restaurantId;
}
