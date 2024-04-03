package com.EatEasy.Controllers;

import com.EatEasy.Dtos.OwnerRequestDto;
import com.EatEasy.Dtos.OwnerResponseDto;
import com.EatEasy.Mapper.OwnerMapper;
import com.EatEasy.Models.Owner;
import com.EatEasy.Services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class OwnerController {

    private final OwnerMapper ownerMapper;
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerMapper ownerMapper, OwnerService ownerService) {
        this.ownerMapper = ownerMapper;
        this.ownerService = ownerService;
    }

    @GetMapping("")
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        log.info("getAllOwners");
        List<Owner> owners = ownerService.findAll();
        List<OwnerResponseDto> responseDtoList = ownerMapper.toResponseDtoList(owners);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> getOwnerById(@PathVariable Long id) {
        log.info("getOwnerById");
        Owner owner = ownerService.findById(id);
        OwnerResponseDto responseDto = ownerMapper.toResponse(owner);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<OwnerResponseDto> createOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        log.info("createOwner");
        Owner owner = ownerMapper.toModel(ownerRequestDto);
        Owner savedOwner = ownerService.save(owner);
        OwnerResponseDto responseDto = ownerMapper.toResponse(savedOwner);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDto> updateOwner(@PathVariable Long id, @RequestBody OwnerRequestDto ownerRequestDto) {
        log.info("updateOwner");
        Owner owner = ownerMapper.toModel(ownerRequestDto);
        Owner updatedOwner = ownerService.update(id, owner);
        OwnerResponseDto responseDto = ownerMapper.toResponse(updatedOwner);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        log.info("deleteOwner");
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
