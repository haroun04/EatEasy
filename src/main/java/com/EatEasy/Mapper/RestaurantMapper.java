package com.EatEasy.Mapper;

import com.EatEasy.Models.Restaurant;
import com.EatEasy.Dtos.RestaurantRequestDto;
import com.EatEasy.Dtos.RestaurantResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    public RestaurantResponseDto toResponse(Restaurant restaurant) {
        return new RestaurantResponseDto(
                restaurant.getId(),
                restaurant.getUuid(),
                restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getFoodStyle(),
                restaurant.getTimeTable(),
                restaurant.getCapacity(),
                restaurant.getPhoneNumber(),
                restaurant.getImages().stream().map(Image::getId).collect(Collectors.toList()),
                restaurant.getBookings().stream().map(Booking::getId).collect(Collectors.toList()),
                restaurant.getReviews().stream().map(Review::getId).collect(Collectors.toList())
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
                0L,
                null,
                restaurantRequestDto.getName(),
                restaurantRequestDto.getLocation(),
                restaurantRequestDto.getFoodStyle(),
                restaurantRequestDto.getTimeTable(),
                restaurantRequestDto.getCapacity(),
                restaurantRequestDto.getPhoneNumber(),
                null,
                null,
                null
        );
    }
}
