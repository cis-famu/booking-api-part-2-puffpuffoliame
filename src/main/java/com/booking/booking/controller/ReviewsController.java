package com.booking.booking.controller;
import com.booking.booking.model.Reviews;
import com.booking.booking.service.ReviewsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/{reviews}")

public class ReviewsController {
    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReviews() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", reviewsService.getAllReviews(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{reviewsId}")
    public ResponseEntity<ApiResponse> getReviewsbyId(@PathVariable String reviewsId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", reviewsService.getReviewsById(reviewsId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }

    }
}
