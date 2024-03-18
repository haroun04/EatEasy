package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDto {
    private Long id;
    private UUID uuid;
    private String name;
    private String email;
    private List<Long> restaurantIds;
    private List<Long> reviewIds;
}
