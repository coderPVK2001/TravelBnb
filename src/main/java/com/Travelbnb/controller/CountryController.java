package com.Travelbnb.controller;

import com.Travelbnb.payload.CountryDto;
import com.Travelbnb.repository.CountryRepository;
import com.Travelbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService cservice;

    public CountryController(CountryService cservice) {
        this.cservice = cservice;
    }

    @PostMapping("/addcountry")
    public ResponseEntity<CountryDto> addcountry(@RequestBody CountryDto dto){

        CountryDto savedDto = cservice.add(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}
