package com.EatEasy.Controllers;

import com.EatEasy.Dtos.RestaurantRequestDto;
import com.EatEasy.Dtos.RestaurantResponseDto;
import com.EatEasy.Mapper.RestaurantMapper;
import com.EatEasy.Models.Restaurant;
import com.EatEasy.Services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Tag(name = "RestaurantController", description = "Operations pertaining to restaurants")
public class RestaurantController {

    private final RestaurantMapper restaurantMapper;
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantMapper restaurantMapper, RestaurantService restaurantService) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        log.info("getAllRestaurants");
        List<Restaurant> restaurants = restaurantService.findAll();
        List<RestaurantResponseDto> responseDtoList = restaurantMapper.toResponse(restaurants);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long id) {
        log.info("getRestaurantById");
        Restaurant restaurant = restaurantService.findById(id);
        RestaurantResponseDto responseDto = restaurantMapper.toResponse(restaurant);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByUUID(@PathVariable UUID uuid) {
        log.info("getRestaurantByUUID");
        Restaurant restaurant = restaurantService.findByUuid(uuid);
        RestaurantResponseDto responseDto = restaurantMapper.toResponse(restaurant);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        log.info("createRestaurant");
        Restaurant restaurant = restaurantMapper.toModel(restaurantRequestDto);
        Restaurant savedRestaurant = restaurantService.save(restaurant);
        RestaurantResponseDto responseDto = restaurantMapper.toResponse(savedRestaurant);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequestDto restaurantRequestDto) {
        log.info("updateRestaurant");
        Restaurant restaurant = restaurantMapper.toModel(restaurantRequestDto);
        Restaurant updatedRestaurant = restaurantService.update(id, restaurant);
        RestaurantResponseDto responseDto = restaurantMapper.toResponse(updatedRestaurant);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        log.info("deleteRestaurant");
        restaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurantsByName(@RequestParam String name) {
        List<Restaurant> restaurants = restaurantService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(restaurants);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<RestaurantResponseDto> patchProduct(
            @PathVariable Long id,
            @RequestBody RestaurantRequestDto restaurantRequestDto
    ) {
        log.info("patchGeneralRestaurant");
        Restaurant restaurantPatched = restaurantService.patch(id, restaurantMapper.toModel(restaurantRequestDto));
        return ResponseEntity.ok(restaurantMapper.toResponse(restaurantPatched));
    }
}
