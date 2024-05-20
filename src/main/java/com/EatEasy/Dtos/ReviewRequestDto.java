package com.EatEasy.Dtos;

import com.EatEasy.Models.Owner;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewRequestDto {
    private final String comment;
    private final Integer assessment;
    private final Long userId;
    private final Long restaurantId;
}
