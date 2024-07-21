package com.Travelbnb.payload;

import lombok.Data;

@Data
public class PropertyDto2 {

    private String hotel_name;
    private Integer no_of_guests;
    private Integer no_of_bathrooms;
    private Integer no_of_bedrooms;
    private Integer pernight_price;

    private String location_name;
    private String country_name;
}
