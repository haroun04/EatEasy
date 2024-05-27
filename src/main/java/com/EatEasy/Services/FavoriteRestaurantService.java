package com.EatEasy.Services;

import com.EatEasy.Models.FavoriteRestaurant;
import java.util.List;

public interface FavoriteRestaurantService {

    List<FavoriteRestaurant> findAll();

    FavoriteRestaurant findById(Long id);

    FavoriteRestaurant save(FavoriteRestaurant favoriteRestaurant);

    void deleteById(Long id);

    FavoriteRestaurant update(Long id, FavoriteRestaurant favoriteRestaurant);

    List<FavoriteRestaurant> getFavoriteRestaurantsByUserId(Long userId);
    FavoriteRestaurant updateFavoriteRestaurant(String token, Long id, Boolean liked);

}
