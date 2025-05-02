package com.kmpalombo.book_review_manager_repo.service;

import com.kmpalombo.book_review_manager_repo.model.Review;
import com.kmpalombo.book_review_manager_repo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // List all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Find a review by ID
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    // Save a new review
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    // Update an existing review
    public Review updateReview(Long id, Review review) {
        Review newReview = reviewRepository.findById(id).orElse(null);
        if (newReview != null) {
            newReview.setBookId(review.getBookId());
            newReview.setReviewerName(review.getReviewerName());
            newReview.setContent(review.getContent());
            newReview.setRating(review.getRating());
            return reviewRepository.save(review);
        }
        return null;
    }

    // Delete a review
    public String deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            return null;
        }
        reviewRepository.deleteById(id);
        return String.valueOf(id);
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}
