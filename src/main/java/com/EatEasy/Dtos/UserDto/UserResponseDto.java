package com.EatEasy.Dtos.UserDto;


import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.Review;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Builder
public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String profilePicture;
    private final String email;
    private final String password;
    private final List<FavoriteRestaurant> favoriteRestaurants;
    private final List<Booking> bookings;
    private final List<Review> reviews;
}
