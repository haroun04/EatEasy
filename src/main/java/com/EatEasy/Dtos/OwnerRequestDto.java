package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequestDto {
    private String name;
    private String email;
    private String password;
    private List<Long> restaurantIds;
    private List<Long> reviewIds;
}
