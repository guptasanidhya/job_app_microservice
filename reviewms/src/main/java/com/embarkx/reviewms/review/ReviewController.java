package com.embarkx.reviewms.review;

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
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){

        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        Review review = reviewService.getReview(reviewId);
        if(review!=null){
            return new ResponseEntity<>(review , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review){
       boolean created = reviewService.createReview(companyId,review);
       if(created) {
           return new ResponseEntity<>("New Review created", HttpStatus.CREATED);
       }
       return new ResponseEntity<>("Review not saved",HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{reviewId}")
    public ResponseEntity<String> updateReview(
                                               @RequestBody Review updatedReview,
                                               @PathVariable Long reviewId) {

        boolean updated = reviewService.updateReview(updatedReview, reviewId);
        if (updated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Update failed",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId){

        boolean deleted = reviewService.deleteReview(reviewId);
        if(deleted){
            return new ResponseEntity<>("Review deleted succesfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
