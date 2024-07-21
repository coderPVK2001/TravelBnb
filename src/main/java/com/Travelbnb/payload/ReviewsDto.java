package com.Travelbnb.payload;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class ReviewsDto {

    @DecimalMax(value = "5" ,message = "rating should be less than or equal to 5 !!")
    @DecimalMin(value = "0" ,message = "rating should be more than 0 and less than 5!!")
    private double rating;
    private String description;
}
