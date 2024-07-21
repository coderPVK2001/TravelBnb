package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.BookingDto;
import com.Travelbnb.payload.BookingDto2;
import com.Travelbnb.payload.PropertyDto;

public interface BookingService {

    BookingDto2 addBooking(BookingDto bookingDto, int propertyId, AppUser appUser);
}
