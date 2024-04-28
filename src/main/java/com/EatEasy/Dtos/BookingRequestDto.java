package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
public class BookingRequestDto {
    private final Integer numberDiners;
   // private final Long userId;
    private final Long restaurantId;
}
