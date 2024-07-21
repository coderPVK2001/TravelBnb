package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Property;
import com.Travelbnb.entity.Reviews;
import com.Travelbnb.exception.PropertyIdNotFoundException;
import com.Travelbnb.exception.ReviewAlreadyGivenException;
import com.Travelbnb.exception.ReviewNotFoundException;
import com.Travelbnb.payload.ReviewsDto;
import com.Travelbnb.payload.ReviewsDto2;
import com.Travelbnb.repository.AppUserRepository;
import com.Travelbnb.repository.PropertyRepository;
import com.Travelbnb.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;
    private AppUserRepository appUserRepository;

    public ReviewServiceImpl(ReviewsRepository reviewsRepository, PropertyRepository propertyRepository, AppUserRepository appUserRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public ReviewsDto2 addReview(ReviewsDto reviewsDto, AppUser appUser, int propertyId) {

        Reviews reviews = DtoToEntity(reviewsDto, appUser, propertyId);
        ReviewsDto2 reviewsDto2 = EntityToDto(reviews);
        return reviewsDto2;
    }

    @Override
    public List<ReviewsDto2> getReviewsList(AppUser appUser) {
        List<Reviews> reviewsList= reviewsRepository.findByUser(appUser);
        List<ReviewsDto2> result = reviewsList.stream().map(x -> EntityToDto(x)).collect(Collectors.toList());
        return result;
    }

    @Override
    public ReviewsDto2 updateReview(AppUser appUser, int propertyId, ReviewsDto reviewsDto) {
        Optional<Property> optional = propertyRepository.findById(propertyId);
        if(optional.isPresent()){
            Property property = optional.get();
            Optional<Reviews> optionalReviews = reviewsRepository.findByUserAndProperty(appUser, property);
            Reviews reviews = optionalReviews.get();

            reviews.setRating(reviewsDto.getRating());
            reviews.setDescription(reviewsDto.getDescription());
            Reviews savedReviews = reviewsRepository.save(reviews);
            ReviewsDto2 reviewsDto2 = EntityToDto(savedReviews);
            return reviewsDto2;
        }
        else {
            throw new PropertyIdNotFoundException("property id:-"+propertyId+ " is invalid!!");
        }
    }


    @Override
    public void deleteReview(AppUser appUser, int propertyId) {
        Optional<Property> optional = propertyRepository.findById(propertyId);
        Property property = optional.get();
        Optional<Reviews> optionalReviews = reviewsRepository.findByUserAndProperty(appUser, property);
        if(optionalReviews.isPresent()){
            Reviews reviews = optionalReviews.get();
            reviewsRepository.deleteById(reviews.getId());
        }
        else{
            throw new ReviewNotFoundException("you didn't gave any review for these property.");
        }
    }

    private Reviews DtoToEntity(ReviewsDto reviewsDto,AppUser appUser, int propertyId){
        Optional<Property> optional = propertyRepository.findById(propertyId);
        if(optional.isPresent()) {
            Property property = optional.get();
            Optional<Reviews> optional123 = reviewsRepository.findByUserAndProperty(appUser, property);
            if(optional123.isPresent()){
                throw new ReviewAlreadyGivenException("already given the review for this property!!");
            }
            else{
                Reviews reviews = new Reviews();
                reviews.setRating(reviewsDto.getRating());
                reviews.setDescription(reviewsDto.getDescription());
                reviews.setAppUser(appUser);
                reviews.setProperty(property);
                Reviews savedReviews = reviewsRepository.save(reviews);
                return savedReviews;
            }
        }
        else {
            throw new PropertyIdNotFoundException("Given property id:"+propertyId +" is invalid!!");
        }
    }

    private ReviewsDto2 EntityToDto(Reviews reviews){
        ReviewsDto2 reviewsDto2 = new ReviewsDto2();
        reviewsDto2.setPropertyName(reviews.getProperty().getName());
        reviewsDto2.setRating(reviews.getRating());
        reviewsDto2.setDescription(reviews.getDescription());
        reviewsDto2.setCountryName(reviews.getProperty().getCountry().getName());
        reviewsDto2.setLocationName(reviews.getProperty().getLocation().getName());

        return reviewsDto2;
    }
}
