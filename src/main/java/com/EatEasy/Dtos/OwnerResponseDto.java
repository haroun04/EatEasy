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
public class OwnerResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final String email;
    private final List<Restaurant> restaurants;
    private final List<Review> reviews;
}
