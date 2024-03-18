package com.EatEasy.Dtos;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class OwnerRequestDto {
    private final String name;
    private final String email;
    private final String password;
}
