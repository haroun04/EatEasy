package com.EatEasy.Dtos;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.Image;
import com.EatEasy.Models.Owner;
import com.EatEasy.Models.Review;
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
    private final String url;
    private final String location;
    private final String foodStyle;
    private final String timeTable;
    private final Integer capacity;
    private final String phoneNumber;
    private final Integer starRating;
    private final String description;
    private final String userIframeSrc;
    private final List<Image> imagesIds;
    private final List<Booking> bookingIds;
    private final List<Review> reviewIds;
    private final Owner owner;
}
