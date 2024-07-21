package com.Travelbnb.payload;

import lombok.Data;

@Data
public class FavouritesDto {

    private String propertyName;
    private boolean status;
    private int perNightPrice;
    private String locationName;
    private String countryName;
}
