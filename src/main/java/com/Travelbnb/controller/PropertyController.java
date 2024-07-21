package com.Travelbnb.controller;

import com.Travelbnb.entity.Property;
import com.Travelbnb.exception.PropertyIdNotFoundException;
import com.Travelbnb.payload.PropertyDto;
import com.Travelbnb.payload.PropertyDto2;
import com.Travelbnb.payload.PropertyUpdateDto;
import com.Travelbnb.repository.PropertyRepository;
import com.Travelbnb.service.PropertyService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private PropertyService propertyService;
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyService pservice, PropertyRepository propertyRepository) {
        this.propertyService = pservice;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addproperty")
    public ResponseEntity<PropertyDto2> addproperties(@RequestBody PropertyDto propertydto){

        PropertyDto2 propertydetails = propertyService.addpropertydetails(propertydto);
        return new ResponseEntity<>(propertydetails, HttpStatus.CREATED);
    }

    @GetMapping("/searchproperties")
    public ResponseEntity<?> getallpropertydetails(
            @RequestParam String name
    ){

        List<PropertyDto2> listproperties = propertyService.findpropertiesByLocationOrCountry(name);
        if(listproperties.isEmpty()){
            return new ResponseEntity<>("No properties found in given location",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(listproperties, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteproperty")
    public ResponseEntity<?> deleteproperty(
            @RequestParam int id
    ){
        Optional<Property> optional = propertyRepository.findById(id);
        if(optional.isPresent()){
            propertyService.delete(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        else {
            throw new PropertyIdNotFoundException("Given Property id:"+id +" is invalid");
        }
    }

    @PutMapping("/updateproperty")
    public ResponseEntity<?> updateProperties(
            @RequestBody PropertyUpdateDto propertyUpdateDto,
            @RequestParam int id
            ){
        Optional<Property> optional = propertyRepository.findById(id);
        if(optional.isPresent()){
            PropertyDto2 propertyDto2 = propertyService.updateProperty(propertyUpdateDto, id);
            return new ResponseEntity<>(propertyDto2,HttpStatus.OK);
        }else{
            throw new PropertyIdNotFoundException("Given Property id:"+id +" is invalid");
        }
    }



}
