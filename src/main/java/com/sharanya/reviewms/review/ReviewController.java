package com.sharanya.reviewms.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReviews(@RequestBody Review review, @RequestParam Long companyId) {
        boolean isReviewSaved = reviewService.createReview(review, companyId);
        if(isReviewSaved)
            return new ResponseEntity<>("review added", HttpStatus.OK);
        else
            return new ResponseEntity<>("review not saved", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){

        Review review = reviewService.getReviewById(reviewId);
        if(review != null)

            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        boolean updated = reviewService.updateReview(reviewId, review);
        if(updated)
            return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean reviewDeleted = reviewService.deleteReview(reviewId);
        if(reviewDeleted)

            return new ResponseEntity<>("review deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
