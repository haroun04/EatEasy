package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FavoriteRestaurantRequestDto {
    private Boolean liked;
    private final Long restaurantId;
}

