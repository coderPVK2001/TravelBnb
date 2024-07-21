package com.Travelbnb.service;

import com.Travelbnb.entity.Country;
import com.Travelbnb.entity.Location;
import com.Travelbnb.entity.Property;
import com.Travelbnb.payload.PropertyDto;
import com.Travelbnb.payload.PropertyDto2;
import com.Travelbnb.payload.PropertyUpdateDto;
import com.Travelbnb.repository.CountryRepository;
import com.Travelbnb.repository.LocationRepository;
import com.Travelbnb.repository.PropertyRepository;
import org.hibernate.property.access.spi.PropertyAccessStrategyResolver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepo;
    private CountryRepository countryRepo;
    private LocationRepository locationRepo;

    public PropertyServiceImpl(PropertyRepository propertyrepo, CountryRepository countryrepo, LocationRepository locationrepo) {
        this.propertyRepo = propertyrepo;
        this.countryRepo = countryrepo;
        this.locationRepo = locationrepo;
    }

    @Override
    public PropertyDto2 addpropertydetails(PropertyDto dto) {

        Property savedProperty = DtoToEntity(dto);
        PropertyDto2 propertyDto2 = EntityToDto(savedProperty);
        return propertyDto2;
    }

    @Override
    public List<PropertyDto2> findpropertiesByLocationOrCountry(String name) {

        List<Property> propList= propertyRepo.finddetails(name);
        List<PropertyDto2> collect = propList.stream().map(x -> EntityToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void delete(int id) {
        propertyRepo.deleteById(id);
    }

    @Override
    public PropertyDto2 updateProperty(PropertyUpdateDto propertyUpdateDto, int id) {
        Property property = propertyRepo.findById(id).get();
        property.setNightlyPrice(propertyUpdateDto.getPerNightPrice());
        Property saved = propertyRepo.save(property);
        PropertyDto2 propertyDto2 = EntityToDto(saved);
        return propertyDto2;
    }

    Property DtoToEntity(PropertyDto dto){

        Property property = new Property();
        property.setName(dto.getName());
        property.setNoBathrooms(dto.getNoBathrooms());
        property.setNoBedrooms(dto.getNoBedrooms());
        property.setNoGuests(dto.getNoGuests());
        property.setNightlyPrice(dto.getNightlyPrice());

        Integer countryId = dto.getCountryid();
        Country country = countryRepo.findById(countryId).get();
        Integer locationId = dto.getLocationid();
        Location location = locationRepo.findById(locationId).get();

        property.setCountry(country);
        property.setLocation(location);
        Property savedProperty = propertyRepo.save(property);

        return savedProperty;
    }

    PropertyDto2 EntityToDto( Property prop){

        PropertyDto2 propDto2 = new PropertyDto2();

        propDto2.setHotel_name(prop.getName());
        propDto2.setNo_of_guests(prop.getNoGuests());
        propDto2.setNo_of_bedrooms(prop.getNoBedrooms());
        propDto2.setNo_of_bathrooms(prop.getNoBathrooms());
        propDto2.setNo_of_guests(prop.getNoGuests());
        propDto2.setPernight_price(prop.getNightlyPrice());

        Location location = prop.getLocation();
        Country country = prop.getCountry();

        propDto2.setLocation_name(location.getName());
        propDto2.setCountry_name(country.getName());

        return propDto2;
    }
}
