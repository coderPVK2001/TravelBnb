package com.Travelbnb.controller;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.ReviewsDto;
import com.Travelbnb.payload.ReviewsDto2;
import com.Travelbnb.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addreview")
    public ResponseEntity<?> addReview(
            @AuthenticationPrincipal AppUser appUser,
            @Valid @RequestBody ReviewsDto reviewsDto,
            BindingResult bindingResult,
            @RequestParam int propertyId
            ){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        ReviewsDto2 reviewsDto2 = reviewService.addReview(reviewsDto, appUser, propertyId);
        return new ResponseEntity<>(reviewsDto2, HttpStatus.CREATED);
    }

    @GetMapping("/getreviews")
    public ResponseEntity<?> reviewslist(
            @AuthenticationPrincipal AppUser appUser
    ){
        List<ReviewsDto2> reviewsList = reviewService.getReviewsList(appUser);
        return new ResponseEntity<>(reviewsList, HttpStatus.OK);
    }

    @PutMapping("/updatereview")
    public ResponseEntity<?> updateReview(
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam int propertyId,
            @RequestBody ReviewsDto reviewsDto
    ){
        ReviewsDto2 reviewsDto2=reviewService.updateReview(appUser ,propertyId, reviewsDto);
        return new ResponseEntity<>(reviewsDto2, HttpStatus.OK);
    }

    @DeleteMapping("/deletereview")
    public ResponseEntity<?> deleteReview(
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam int propertyId
    ){
        reviewService.deleteReview(appUser, propertyId);
        return new ResponseEntity<>("Deleted review successfully!! ", HttpStatus.OK);
    }

}
