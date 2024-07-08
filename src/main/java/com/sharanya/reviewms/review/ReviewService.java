package com.sharanya.reviewms.review;


import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    boolean createReview(Review review, Long companyId);
    Review getReviewById(Long reviewId);

    boolean updateReview(Long reviewId, Review review);

    boolean deleteReview(Long reviewId);
}
