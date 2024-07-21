package com.Travelbnb.repository;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Favourites;
import com.Travelbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {

    @Query("SELECT f from Favourites f where f.appUser=:appuser and property=:property")
    Favourites findByUserAndProperty(@Param("appuser") AppUser appUser,@Param("property") Property property);

}