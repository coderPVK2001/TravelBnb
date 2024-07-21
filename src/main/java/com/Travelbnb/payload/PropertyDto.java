package com.Travelbnb.payload;

import lombok.Data;

@Data
public class PropertyDto {

    private String name;
    private Integer noGuests;
    private Integer noBathrooms;
    private Integer noBedrooms;
    private Integer nightlyPrice;

    private Integer locationid;
    private Integer countryid;
}
