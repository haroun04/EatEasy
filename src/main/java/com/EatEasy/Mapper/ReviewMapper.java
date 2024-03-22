package com.EatEasy.Mapper;
import com.EatEasy.Dtos.OwnerResponseDto;
import com.EatEasy.Models.Owner;
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
    private final OwnerMapper ownerMapper;

    //Constructor por defecto, autowired hay que ponerlo
    @Autowired
    public ReviewMapper(UserMapper userMapper, RestaurantMapper restaurantMapper, OwnerMapper ownerMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
        this.ownerMapper = ownerMapper;
    }


    public ReviewResponseDto toResponse(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getUuid(),
                review.getComment(),
                review.getAssessment(),
                review.getCreatedAt(),
                review.getUser().getId(),
                review.getRestaurant().getId(),
                review.getOwner().getId()
        );
    }

    public List<ReviewResponseDto> toResponseDtoList(List<Review> reviews) {
        return reviews.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Review toModel(ReviewRequestDto reviewRequestDto) {
        return new Review(
                null,
                UUID.randomUUID(),
                reviewRequestDto.getComment(),
                reviewRequestDto.getAssessment(),
                reviewRequestDto.getCreatedAt(),
                null,
                null,
                null
        );
    }
}