package com.EatEasy.Services;

import com.EatEasy.Models.Review;
import com.EatEasy.Models.User;
import com.EatEasy.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findReviewByRestaurantId(Long restaurantId) {
        return reviewRepository.findByRestaurant_Id(restaurantId);
    }

    @Override
    public String findUserNameByReviewId(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            User user = review.getUser();
            return user != null ? user.getName() : null;
        } else {
            return null; // O lanzar una excepción si se desea un comportamiento diferente cuando la revisión no se encuentra
        }
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Review update(Long id, Review model) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();
            review.setComment(model.getComment());
            review.setAssessment(model.getAssessment());
            return reviewRepository.save(review);
        }
        return null;
    }
}
