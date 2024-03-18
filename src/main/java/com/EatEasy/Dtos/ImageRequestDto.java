package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ImageRequestDto {
    private final String url;
    private final Long restaurantId;
}
