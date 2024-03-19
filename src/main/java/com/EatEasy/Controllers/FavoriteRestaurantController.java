package com.EatEasy.Controllers;

import com.EatEasy.Dtos.FavoriteRestaurantRequestDto;
import com.EatEasy.Dtos.FavoriteRestaurantResponseDto;
import com.EatEasy.Mapper.FavoriteRestaurantMapper;
import com.EatEasy.Services.FavoriteRestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-restaurants")
@Slf4j
public class FavoriteRestaurantController {
    private final FavoriteRestaurantMapper favoriteRestaurantMapper;
    private final FavoriteRestaurantService favoriteRestaurantService;

    @Autowired
    public FavoriteRestaurantController(FavoriteRestaurantMapper favoriteRestaurantMapper, FavoriteRestaurantService favoriteRestaurantService) {
        this.favoriteRestaurantMapper = favoriteRestaurantMapper;
        this.favoriteRestaurantService = favoriteRestaurantService;
    }

    @GetMapping("")
    public ResponseEntity<List<FavoriteRestaurantResponseDto>> getAllFavoriteRestaurants(){
        log.info("getAllFavoriteRestaurants");
        return ResponseEntity.ok(
                favoriteRestaurantMapper.toResponse(favoriteRestaurantService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteRestaurantResponseDto> getFavoriteRestaurantByID(@PathVariable Long id){
        log.info("getFavoriteRestaurantByID");
        return ResponseEntity.ok(
                favoriteRestaurantMapper.toResponse(favoriteRestaurantService.findById(id))
        );
    }

    @PostMapping("")
    public ResponseEntity<FavoriteRestaurantResponseDto> createFavoriteRestaurant(@RequestBody FavoriteRestaurantRequestDto favoriteRestaurantRequestDto){
        log.info("createFavoriteRestaurant");
        FavoriteRestaurantResponseDto favoriteRestaurantResponseDto = favoriteRestaurantMapper.toResponse(favoriteRestaurantService.save(favoriteRestaurantMapper.toModel(favoriteRestaurantRequestDto)));
        return ResponseEntity.ok(favoriteRestaurantResponseDto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteFavoriteRestaurant(@PathVariable Long id){
        log.info("deleteFavoriteRestaurant");
        favoriteRestaurantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
