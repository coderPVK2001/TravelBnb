package com.Travelbnb.payload;

import lombok.Data;

@Data
public class ReviewsDto2 {

    private String propertyName;
    private double rating;
    private String description;
    private String countryName;
    private String locationName;
}
