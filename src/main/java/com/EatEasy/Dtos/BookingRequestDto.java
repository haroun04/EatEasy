package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class BookingRequestDto {
    private final Integer numberDiners;
    private final LocalDateTime reservedAt;
    private final Long userId;
    private final Long restaurantId;
}
