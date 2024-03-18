package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ReviewResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String comment;
    private final Integer assessment;
    private final LocalDateTime createdAt;
    private final Long userId;
    private final Long restaurantId;
}
