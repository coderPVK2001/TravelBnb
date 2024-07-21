package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Favourites;
import com.Travelbnb.entity.Property;
import com.Travelbnb.exception.FavouriteException;
import com.Travelbnb.exception.PropertyIdNotFoundException;
import com.Travelbnb.payload.FavouritesDto;
import com.Travelbnb.repository.FavouritesRepository;
import com.Travelbnb.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService{

    private FavouritesRepository favouritesRepository;
    private PropertyRepository propertyRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository, PropertyRepository propertyRepository) {
        this.favouritesRepository = favouritesRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public FavouritesDto addfavourite(Favourites favourites, AppUser appUser,int propertyId) {

        Optional<Property> optional = propertyRepository.findById(propertyId);

        if(optional.isPresent()){

            Property property = optional.get();
            Favourites favv = favouritesRepository.findByUserAndProperty(appUser, property);
            if(favv==null) {
                favourites.setProperty(property);
                favourites.setAppUser(appUser);
                Favourites savedFavourites = favouritesRepository.save(favourites);

                FavouritesDto favouritesDto = entityToDto(savedFavourites);
                return favouritesDto;
            }
            else{
                throw new FavouriteException("Already added in favourites !!");
            }
        }
        else {
            throw new PropertyIdNotFoundException("Property id:-"+propertyId+"is invalid");
        }
    }

    public FavouritesDto entityToDto(Favourites favourites){

        FavouritesDto favouritesDto=new FavouritesDto();
        favouritesDto.setPropertyName(favourites.getProperty().getName());
        favouritesDto.setStatus(favourites.getStatus());
        favouritesDto.setPerNightPrice(favourites.getProperty().getNightlyPrice());
        favouritesDto.setCountryName(favourites.getProperty().getCountry().getName());
        favouritesDto.setLocationName(favourites.getProperty().getLocation().getName());
        return favouritesDto;
    }
}
