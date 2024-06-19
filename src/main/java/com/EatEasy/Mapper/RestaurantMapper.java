package com.EatEasy.Mapper;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Dtos.RestaurantRequestDto;
import com.EatEasy.Dtos.RestaurantResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    private final OwnerMapper ownerMapper;

    @Autowired
    public RestaurantMapper(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    public RestaurantResponseDto toResponse(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getUuid(),
                restaurant.getName(),
                restaurant.getUrl(),
                restaurant.getLocation(),
                restaurant.getFoodStyle(),
                restaurant.getTimetable(),
                restaurant.getCapacity(),
                restaurant.getPhoneNumber(),
                restaurant.getStarRating(),
                restaurant.getDescription(),
                restaurant.getUserIframeSrc(),
                restaurant.getImages(),
                restaurant.getBookings(),
                restaurant.getReviews(),
                restaurant.getOwner()
        );
    }

    public List<RestaurantResponseDto> toResponse(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Restaurant toModel(RestaurantRequestDto restaurantRequestDto) {
        return new Restaurant(
                null,
                UUID.randomUUID(),
                restaurantRequestDto.getName(),
                restaurantRequestDto.getUrl(),
                restaurantRequestDto.getLocation(),
                restaurantRequestDto.getFoodStyle(),
                restaurantRequestDto.getTimeTable(),
                restaurantRequestDto.getCapacity(),
                restaurantRequestDto.getPhoneNumber(),
                restaurantRequestDto.getStarRating(),
                restaurantRequestDto.getDescription(),
                restaurantRequestDto.getUserIframeSrc(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                restaurantRequestDto.getOwner_id() != null ? ownerMapper.toModelfromRequestDto(restaurantRequestDto.getOwner_id()) : null
        );
    }

    public Restaurant toModelFromRequestDto(Long restaurantId) {
        return new Restaurant(
                restaurantId,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
