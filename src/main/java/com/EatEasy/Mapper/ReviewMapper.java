package com.EatEasy.Mapper;
import com.EatEasy.Dtos.OwnerResponseDto;
import com.EatEasy.Models.Owner;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Models.Review;
import com.EatEasy.Dtos.ReviewRequestDto;
import com.EatEasy.Dtos.ReviewResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReviewMapper {
    private final RestaurantMapper restaurantMapper;
    private final UserMapper userMapper;
    @Autowired
    public ReviewMapper(RestaurantMapper restaurantMapper, UserMapper userMapper) {
     //   this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
        this.userMapper = userMapper;
    }


    public ReviewResponseDto toResponse(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getUuid(),
                review.getComment(),
                review.getAssessment(),
                review.getCreatedAt(),
                review.getUser(),
                review.getRestaurant()
        );
    }

    public List<ReviewResponseDto> toResponseDtoList(List<Review> reviews) {
        return reviews.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Review toModel(ReviewRequestDto reviewRequestDto) {
        return new Review(
                0L,
                UUID.randomUUID(),
                reviewRequestDto.getComment(),
                reviewRequestDto.getAssessment(),
                LocalDateTime.now(),
                reviewRequestDto.getRestaurantId() != null ? restaurantMapper.toModelFromRequestDto(reviewRequestDto.getRestaurantId()) : null,
                reviewRequestDto.getUserId() !=null ? userMapper.toModelFromRequestDto(reviewRequestDto.getUserId()) : null
        );
    }
}