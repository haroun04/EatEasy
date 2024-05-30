package com.EatEasy.Services;

import com.EatEasy.Models.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant findByUuid(UUID uuid);

    Restaurant save(Restaurant restaurant);

    void deleteById(Long id);

    Restaurant update(Long id, Restaurant restaurant);
    List<Restaurant> findByNameContainingIgnoreCase(String name);

    Restaurant patch(Long id, Restaurant restaurant);
}
