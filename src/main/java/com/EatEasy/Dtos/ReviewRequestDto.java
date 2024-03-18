package com.EatEasy.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {
    private String comment;
    private Integer assessment;
    //private LocalDateTime createdAt;
    //private Long userId;
    //private Long restaurantId;
}
