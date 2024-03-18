package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ImageResponseDto {
    private final Long id;
    private final String url;
    private final Restaurant  restaurantId;
}
