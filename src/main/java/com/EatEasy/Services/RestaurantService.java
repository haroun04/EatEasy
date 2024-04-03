package com.EatEasy.Services;

import com.EatEasy.Models.Restaurant;
import java.util.List;

public interface RestaurantService {
    List<Restaurant> findAll();

    Restaurant findById(Long id);

    Restaurant save(Restaurant restaurant);

    void deleteById(Long id);

    Restaurant update(Long id, Restaurant restaurant);
    List<Restaurant> findByNameContainingIgnoreCase(String name);
}
