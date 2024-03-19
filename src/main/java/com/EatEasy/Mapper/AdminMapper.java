package com.EatEasy.Mapper;

import com.EatEasy.Models.Admin;
import com.EatEasy.Dtos.AdminRequestDto;
import com.EatEasy.Dtos.AdminResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AdminMapper {

    public AdminResponseDto toResponse(Admin admin) {
        return new AdminResponseDto(
                admin.getId(),
                admin.getUuid(),
                admin.getName()
        );
    }

    public List<AdminResponseDto> toResponse(List<Admin> admins) {
        return admins.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Admin toModel(AdminRequestDto adminRequestDto) {
        return new Admin(
                0L,
                UUID.randomUUID(),
                adminRequestDto.getName(),
                adminRequestDto.getPassword()
        );
    }

    public Admin toModel(Long adminId) {
        return new Admin(
                adminId,
                null,
                null,
                null
        );
    }
}

