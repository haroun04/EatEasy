package com.EatEasy.Dtos;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.Image;
import com.EatEasy.Models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RestaurantRequestDto {
    private final String name;
    private final String location;
    private final String foodStyle;
    private final String timeTable;
    private final Integer capacity;
    private final String phoneNumber;
}
