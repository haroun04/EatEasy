package com.EatEasy.Mapper;

import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Dtos.FavoriteRestaurantRequestDto;
import com.EatEasy.Dtos.FavoriteRestaurantResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class FavoriteRestaurantMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public FavoriteRestaurantMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public FavoriteRestaurantResponseDto toResponse(FavoriteRestaurant favoriteRestaurant) {
        return new FavoriteRestaurantResponseDto(
                favoriteRestaurant.getId(),
                favoriteRestaurant.getLiked(),
              //  favoriteRestaurant.getUser() != null ? favoriteRestaurant.getUser().getId() : null,
                favoriteRestaurant.getRestaurant()

        );
    }

    public List<FavoriteRestaurantResponseDto> toResponse(List<FavoriteRestaurant> favoriteRestaurants) {
        return favoriteRestaurants.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public FavoriteRestaurant toModel(FavoriteRestaurantRequestDto favoriteRestaurantRequestDto) {
        return new FavoriteRestaurant(
                0L,
                favoriteRestaurantRequestDto.getLiked(),
                null,
                ///favoriteRestaurantRequestDto.getUserId() != null ? userMapper.toModelFromRequestDto(favoriteRestaurantRequestDto.getUserId()) : null,
                favoriteRestaurantRequestDto.getRestaurantId() != null ? restaurantMapper.toModelFromRequestDto(favoriteRestaurantRequestDto.getRestaurantId()) : null

        );
    }
}
