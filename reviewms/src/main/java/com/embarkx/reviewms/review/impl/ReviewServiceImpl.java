package com.embarkx.reviewms.review.impl;


import com.embarkx.reviewms.review.Review;
import com.embarkx.reviewms.review.ReviewRepository;
import com.embarkx.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    private ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {

      if (companyId!=null && review!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview( Long reviewId) {
       return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Review updatedReview, Long reviewId) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        }
    return false;
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
