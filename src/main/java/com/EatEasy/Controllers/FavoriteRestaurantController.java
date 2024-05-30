package com.EatEasy.Controllers;

import com.EatEasy.Dtos.FavoriteRestaurantRequestDto;
import com.EatEasy.Dtos.FavoriteRestaurantResponseDto;
import com.EatEasy.Mapper.FavoriteRestaurantMapper;
import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import com.EatEasy.Models.user.User;
import com.EatEasy.Repository.UserDetailsRepository;
import com.EatEasy.Services.FavoriteRestaurantService;
import com.EatEasy.auth.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-restaurants")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteRestaurantController {
    private final FavoriteRestaurantMapper favoriteRestaurantMapper;
    private final  FavoriteRestaurantService favoriteRestaurantService;
    private final UserDetailsRepository userDetailsRepository;
    @Autowired
    public FavoriteRestaurantController(FavoriteRestaurantMapper favoriteRestaurantMapper, FavoriteRestaurantService favoriteRestaurantService, UserDetailsRepository userDetailsRepository) {
        this.favoriteRestaurantMapper = favoriteRestaurantMapper;
        this.favoriteRestaurantService = favoriteRestaurantService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<FavoriteRestaurantResponseDto>> getAllFavoriteRestaurants(){
        log.info("getAllFavoriteRestaurants");
        return ResponseEntity.ok(
                favoriteRestaurantMapper.toResponse(favoriteRestaurantService.findAll())
        );
    }

    @GetMapping("/restaurantId/{id}")
    public ResponseEntity<FavoriteRestaurantResponseDto> getFavoriteRestaurantByRestaurantID(@PathVariable Long id) {
        log.info("getFavoriteRestaurantByRestaurantID");
        return ResponseEntity.ok(
                favoriteRestaurantMapper.toResponse(favoriteRestaurantService.findByRestaurantId(id))
        );
    }


    @GetMapping("/{restaurantId}/isFavorite")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long restaurantId) {
        log.info("Checking if restaurant with ID {} is a favorite", restaurantId);
        boolean isFavorite = favoriteRestaurantService.isFavorite(restaurantId);
        return ResponseEntity.ok(isFavorite);
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<FavoriteRestaurant>> getFavoriteRestaurantsByUserId(@PathVariable Long userId) {
        List<FavoriteRestaurant> favoriteRestaurants = favoriteRestaurantService.getFavoriteRestaurantsByUserId(userId);
        return ResponseEntity.ok(favoriteRestaurants);
    }
    @Autowired
    private JWTService jwtService;
    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteRestaurant>> getFavoriteRestaurants(@RequestHeader("Authorization") String token) {
        String authToken = token.substring(7); // Eliminar "Bearer " del token
        String email = jwtService.getUsernameFromToken(authToken);
        User user = userDetailsRepository.findByEmail(email);

        List<FavoriteRestaurant> favoriteRestaurants = favoriteRestaurantService.getFavoriteRestaurantsByUserId(user.getId());
        return ResponseEntity.ok(favoriteRestaurants);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<FavoriteRestaurant> updateFavoriteRestaurant(
            @RequestHeader(name = "Authorization") String token,
            @PathVariable Long id,
            @RequestBody Boolean liked) {
        FavoriteRestaurant updatedFavoriteRestaurant = favoriteRestaurantService.updateFavoriteRestaurant(token, id, liked);
        return ResponseEntity.ok(updatedFavoriteRestaurant);
    }

}
