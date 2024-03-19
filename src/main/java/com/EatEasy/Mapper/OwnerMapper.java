package com.EatEasy.Mapper;

import com.EatEasy.Models.Owner;
import com.EatEasy.Dtos.OwnerRequestDto;
import com.EatEasy.Dtos.OwnerResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerMapper {

    public OwnerResponseDto toResponseDto(Owner owner) {
        return new OwnerResponseDto(
                owner.getId(),
                owner.getUuid(),
                owner.getName(),
                owner.getEmail(),
                owner.getRestaurants(),
                owner.getReviews()
        );
    }

    public List<OwnerResponseDto> toResponseDtoList(List<Owner> owners) {
        return owners.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public Owner toModel(OwnerRequestDto ownerRequestDto) {
        return new Owner(
                0L,
                null,
                ownerRequestDto.getName(),
                ownerRequestDto.getEmail(),
                ownerRequestDto.getPassword(),
                null,
                null
        );
    }

}