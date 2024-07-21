package com.Travelbnb.controller;

import com.Travelbnb.entity.Location;
import com.Travelbnb.payload.LocationDto;
import com.Travelbnb.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {

    private LocationService lservice;

    public LocationController(LocationService lservice) {
        this.lservice = lservice;
    }

    @PostMapping("/addlocation")
    public ResponseEntity<LocationDto> addlocation(@RequestBody LocationDto ldto){

        LocationDto locationdto= lservice.addData(ldto);

        return new ResponseEntity<>(locationdto, HttpStatus.CREATED);
    }


}
