package com.EatEasy.Mapper;
import com.EatEasy.Dtos.ReviewRequestDto;
import com.EatEasy.Dtos.ReviewResponseDto;
import com.EatEasy.Models.Review;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
public class ReviewMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public ReviewMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public ReviewResponseDto toResponse(Review review) {
        if (review != null) {
            return new ReviewResponseDto(
                    review.getId(),
                    review.getUuid(),
                    review.getComment(),
                    review.getAssessment(),
                    review.getCreatedAt(),
                    review.getUser(),
                    review.getRestaurant()
            );
        } else {
            return null;
        }
    }

    public Review toModel(ReviewRequestDto reviewRequestDto) {
        return new Review(
                0L,
                UUID.randomUUID(),
                reviewRequestDto.getComment(),
                reviewRequestDto.getAssessment(),
                LocalDateTime.now(),
                null,
                null
        );
    }
}