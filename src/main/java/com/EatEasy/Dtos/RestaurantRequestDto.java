package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;
    private String location;
    private String foodStyle;
    private String timeTable;
    private Integer capacity;
    private String phoneNumber;
    private List<Long> imageIds;
    private List<Long> bookingIds;
    private List<Long> reviewIds;
}
