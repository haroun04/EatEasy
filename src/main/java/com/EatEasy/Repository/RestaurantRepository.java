package com.EatEasy.Repository;

import com.EatEasy.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByUuid(UUID uuid);
    List<Restaurant> findByNameContainingIgnoreCase(String name);
    List<Restaurant> findByLocationContainingIgnoreCase(String location);
    List<Restaurant> findByFoodStyleContainingIgnoreCase(String foodStyle);
}
