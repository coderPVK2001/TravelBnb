package com.Travelbnb.controller;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.BookingDto;
import com.Travelbnb.payload.BookingDto2;
import com.Travelbnb.service.BookingService;
import com.Travelbnb.service.TwilioSmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;

    }

    @PostMapping("/book")
    public ResponseEntity<?> booking(
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam int propertyId,
            @RequestBody BookingDto bookingDto
            ){

        BookingDto2 bookingDto2 = bookingService.addBooking(bookingDto, propertyId, appUser);
        return new ResponseEntity<>(bookingDto2, HttpStatus.CREATED);
    }
}
