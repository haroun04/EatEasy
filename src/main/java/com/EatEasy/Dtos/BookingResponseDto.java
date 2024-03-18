package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
    private Long id;
    private UUID uuid;
    private Integer numberDiners;
    private LocalDateTime createdAt;
    private Long userId;
    private Long restaurantId;
}
