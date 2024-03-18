package com.EatEasy.Repository;

import com.EatEasy.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUuid(UUID uuid);
    List<Review> findByRestaurant_Id(Long restaurantId);
    List<Review> findByUser_Id(Long userId);
}
