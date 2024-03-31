package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestaurantRequestDto {
    private final String name;
    private final String url;
    private final String location;
    private final String foodStyle;
    private final String timeTable;
    private final Integer capacity;
    private final String phoneNumber;
    private final Long owner_id;
}
