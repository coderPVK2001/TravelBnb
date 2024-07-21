package com.Travelbnb.service;

import com.Travelbnb.entity.Location;
import com.Travelbnb.payload.LocationDto;
import com.Travelbnb.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private LocationRepository locationRepo;

    public LocationService(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public LocationDto addData(LocationDto dto){

        Location locationentity = DtoToEntity(dto);
        LocationDto locationDto = EntityToDto(locationentity);

        return locationDto;
    }

    Location DtoToEntity(LocationDto dto){

        Location loc= new Location();
        loc.setName(dto.getName());
        Location savedLocation = locationRepo.save(loc);

        return savedLocation;
    }

    LocationDto EntityToDto(Location location){

        LocationDto dto=new LocationDto();
        dto.setId(location.getId());
        dto.setName(location.getName());

        return dto;
    }
}
