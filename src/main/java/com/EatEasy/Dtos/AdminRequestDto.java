package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AdminRequestDto {
    private final String name;
    private final String password;
}
