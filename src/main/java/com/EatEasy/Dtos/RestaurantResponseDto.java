package com.EatEasy.Dtos;

import com.EatEasy.Models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RestaurantResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
    private final String location;
    private final String foodStyle;
    private final String timeTable;
    private final Integer capacity;
    private final String phoneNumber;
    private final List<Image> images;
    private final List<Booking> bookingIds;
    private final List<Review> reviewIds;
    private final List<FavoriteRestaurant>favoriteRestaurantsIds;
    private final Owner owner;
}
