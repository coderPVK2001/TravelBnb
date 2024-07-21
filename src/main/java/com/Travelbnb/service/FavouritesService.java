package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Favourites;
import com.Travelbnb.payload.FavouritesDto;

public interface FavouritesService {

    FavouritesDto addfavourite(Favourites favourites, AppUser appUser, int propertyId);
}
