package com.Travelbnb.service;

import com.Travelbnb.entity.Property;
import com.Travelbnb.payload.PropertyDto;
import com.Travelbnb.payload.PropertyDto2;
import com.Travelbnb.payload.PropertyUpdateDto;

import java.util.List;

public interface PropertyService {

    PropertyDto2 addpropertydetails(PropertyDto dto);

    List<PropertyDto2> findpropertiesByLocationOrCountry(String name);

    void delete(int id);

    PropertyDto2 updateProperty(PropertyUpdateDto propertyUpdateDto, int id);
}
