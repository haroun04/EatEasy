package com.EatEasy.Mapper;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import com.EatEasy.Dtos.ReviewRequestDto;
import com.EatEasy.Dtos.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {
    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    //Constructor por defecto, autowired hay que ponerlo
    @Autowired
    public ReviewMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }


    public ReviewResponseDto toResponseDto(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getUuid(),
                review.getComment(),
                review.getAssessment(),
                review.getCreatedAt(),
                review.getUser().getId(),
                review.getRestaurant().getId()
        );
    }

    public List<ReviewResponseDto> toResponseDtoList(List<Review> reviews) {
        return reviews.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public Review toModel(ReviewRequestDto reviewRequestDto) {
        return new Review(
                null, // Dejar el ID como nulo o 0, dependiendo de cómo se maneje en la clase Review
                UUID.randomUUID(), // Generar un UUID nuevo
                reviewRequestDto.getComment(),
                reviewRequestDto.getAssessment(),
                reviewRequestDto.getCreatedAt(),
                null, // Dejar el usuario como nulo para asignarlo más tarde
                null // Dejar el restaurante como nulo para asignarlo más tarde
        );
    }
}