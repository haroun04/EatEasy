package com.EatEasy.Repository;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long> {
    void deleteByRestaurantId(Long restaurantId);
    List<FavoriteRestaurant> findByUser_Id(Long userId);



}
