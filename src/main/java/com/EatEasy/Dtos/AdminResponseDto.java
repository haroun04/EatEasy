package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AdminResponseDto {
    private final Long id;
    private final UUID uuid;
    private final String name;
}
