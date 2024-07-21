package com.Travelbnb.controller;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Favourites;
import com.Travelbnb.payload.FavouritesDto;
import com.Travelbnb.service.FavouritesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouritesController {

    private FavouritesService favouritesService;

    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @PostMapping("/status")
    public ResponseEntity<?> addFavourite(@RequestBody Favourites favourites,
                                          @AuthenticationPrincipal AppUser appUser,
                                          @RequestParam int propertyId){

        FavouritesDto favouritesDto = favouritesService.addfavourite(favourites, appUser, propertyId);
        return new ResponseEntity<>(favouritesDto, HttpStatus.CREATED);
    }

}
