package com.Travelbnb.service;

import com.Travelbnb.entity.Country;
import com.Travelbnb.entity.Location;
import com.Travelbnb.payload.CountryDto;
import com.Travelbnb.payload.LocationDto;
import com.Travelbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository countryRepo;
    public CountryService(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    public CountryDto add(CountryDto cDto){
        Country savedCountry = dtoToEntity(cDto);
        CountryDto countryDto = entityToDto(savedCountry);
        return countryDto;
    }

    Country dtoToEntity(CountryDto dto){
        Country country= new Country();
        country.setName(dto.getName());
        Country savedCountry = countryRepo.save(country);
        return savedCountry;
    }



    CountryDto entityToDto(Country country){
        CountryDto countryDto=new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        return countryDto;
    }
}
