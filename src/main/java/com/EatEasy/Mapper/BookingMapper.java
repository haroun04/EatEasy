package com.EatEasy.Mapper;

import com.EatEasy.Models.Booking;
import com.EatEasy.Dtos.BookingRequestDto;
import com.EatEasy.Dtos.BookingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BookingMapper {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public BookingMapper(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public BookingResponseDto toResponse(Booking booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getUuid(),
                booking.getNumberDiners(),
                booking.getCreatedAt(),
                booking.getUser(),
                booking.getRestaurant()
        );
    }

    public List<BookingResponseDto> toResponse(List<Booking> bookings) {
        return bookings.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Booking toModel(BookingRequestDto bookingRequestDto) {
        return new Booking(
                0L,
                UUID.randomUUID(),
                bookingRequestDto.getNumberDiners(),
                null,
                bookingRequestDto.getUserId() != null ? userMapper.toModelfromRequestDto(bookingRequestDto.getUserId()) : null,
                bookingRequestDto.getRestaurantId() != null ? restaurantMapper.toModelfromRequestDto(bookingRequestDto.getRestaurantId()) : null
        );
    }
}
