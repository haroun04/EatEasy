package com.EatEasy.Repository;

import com.EatEasy.Models.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long> {
}