package com.EatEasy.Mapper;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import com.EatEasy.Dtos.ReviewRequestDto;
import com.EatEasy.Dtos.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
        Review review = new Review();
        review.setComment(reviewRequestDto.getComment());
        review.setAssessment(reviewRequestDto.getAssessment());
        review.setCreatedAt(reviewRequestDto.getCreatedAt());
        // Los IDs de usuario y restaurante se asignarán como referencias más adelante
        return review;
    }
}