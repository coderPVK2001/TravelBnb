package com.Travelbnb.payload;

import lombok.Data;

@Data
public class JwtTokenDto {

    private String type;

    private String name;
}
