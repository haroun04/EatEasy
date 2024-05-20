package com.EatEasy.Controllers;
import com.EatEasy.Dtos.ReviewRequestDto;
import com.EatEasy.Dtos.ReviewResponseDto;
import com.EatEasy.Mapper.ReviewMapper;
import com.EatEasy.Models.Review;
import com.EatEasy.Services.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewMapper reviewMapper, ReviewService reviewService) {
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        log.info("getAllReviews");
        List<Review> reviews = reviewService.findAll();
        List<ReviewResponseDto> responseDtoList = reviewMapper.toResponseDtoList(reviews);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> getReviewById(@PathVariable Long id) {
        log.info("getReviewById");
        Review review = reviewService.findById(id);
        ReviewResponseDto responseDto = reviewMapper.toResponse(review);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        log.info("createReview");
        Review review = reviewMapper.toModel(reviewRequestDto);
        Review savedReview = reviewService.save(review); // Guarda la revisión una vez
        ReviewResponseDto responseDto = reviewMapper.toResponse(savedReview);

        return ResponseEntity.ok(responseDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewRequestDto) {
        log.info("updateReview");
        Review review = reviewMapper.toModel(reviewRequestDto);
        Review updatedReview = reviewService.update(id, review);
        ReviewResponseDto responseDto = reviewMapper.toResponse(updatedReview);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.info("deleteReview");
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //POR ID DE RESTAURANTE

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Review>> getReviewsByRestaurantId(@PathVariable Long restaurantId) {
        List<Review> reviews = reviewService.findReviewByRestaurantId(restaurantId);
        return ResponseEntity.ok(reviews);
    }


    //POR ID DE REVIEW DEVUELVE NAME
    @GetMapping("/{id}/user")
    public ResponseEntity<String> getUserNameByReviewId(@PathVariable Long id) {
        log.info("getUserNameByReviewId");
        String userName = reviewService.findUserNameByReviewId(id);
        return ResponseEntity.ok(userName);
    }

    //POR ID DE REVIEW DEVUELVE FOTO DE PERFIL
    @GetMapping("/{id}/userProfilePicture")
    public ResponseEntity<String> getUserProfilePictureByReviewId(@PathVariable Long id) {
        log.info("getUserProfilePictureByReviewId");
        String userProfilePicture = reviewService.getUserProfilePictureByReviewId(id);
        return ResponseEntity.ok(userProfilePicture);
    }


}