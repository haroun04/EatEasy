package com.EatEasy.Mapper;

import com.EatEasy.Models.Image;
import com.EatEasy.Dtos.ImageRequestDto;
import com.EatEasy.Dtos.ImageResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    private final RestaurantMapper restaurantMapper;

    public ImageMapper(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
    }

    public ImageResponseDto toResponse(Image image) {
        return new ImageResponseDto(
                image.getId(),
                image.getUrl(),
                image.getRestaurant()
        );
    }

    public List<ImageResponseDto> toResponse(List<Image> images) {
        return images.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Image toModel(ImageRequestDto imageRequestDto) {
        return new Image(
                0L,
                imageRequestDto.getUrl(),
                imageRequestDto.getRestaurantId() != null ?
                        restaurantMapper.toModelfromRequestDto(imageRequestDto.getRestaurantId()) : null
        );
    }
}
