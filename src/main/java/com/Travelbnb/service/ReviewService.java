package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.ReviewsDto;
import com.Travelbnb.payload.ReviewsDto2;

import java.util.List;

public interface ReviewService {

    ReviewsDto2 addReview(ReviewsDto reviewsDto, AppUser appUser, int propertyId);

    List<ReviewsDto2> getReviewsList(AppUser appUser);

    ReviewsDto2 updateReview(AppUser appUser, int propertyId, ReviewsDto reviewsDto);

    void deleteReview(AppUser appUser, int propertyId);
}
