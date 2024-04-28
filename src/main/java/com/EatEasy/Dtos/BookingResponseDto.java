package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BookingResponseDto {
    private final Long id;
    private final UUID uuid;
    private final Integer numberDiners;
    private final LocalDateTime createdAt;
    private final LocalDateTime reservedAt;
  //  private final User userId;
    private final Restaurant restaurantId;
}
