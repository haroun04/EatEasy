package com.EatEasy.Dtos;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final String email;
    private final List<FavoriteRestaurant> favoriteRestaurants;
    private final List<Booking> bookings;
    private final List<Review> reviews;
}
