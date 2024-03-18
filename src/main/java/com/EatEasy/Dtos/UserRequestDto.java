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
public class UserRequestDto {
    private final String name;
    private final String email;
    private final String password;
}
