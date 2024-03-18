package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponseDto {
    private Long id;
    private String url;
    private Restaurant restaurantId;
}
