package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDto {
    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private List<Restaurant> restaurants;
    private List<Review> reviews;
}
