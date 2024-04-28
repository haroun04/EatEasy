package com.EatEasy.Services;

import com.EatEasy.Models.Review;
import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    List<Review> findReviewByRestaurantId(Long restaurantId);

    String findUserNameByReviewId(Long id);

    String getUserProfilePictureByReviewId(Long id);


    Review findById(Long id);

    Review save(Review review);

    void deleteById(Long id);

    Review update(Long id, Review review);
}
