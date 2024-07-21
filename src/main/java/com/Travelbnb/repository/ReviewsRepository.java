package com.Travelbnb.repository;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.entity.Property;
import com.Travelbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Integer> {

    @Query("SELECT r FROM Reviews r WHERE r.appUser=:appuserone AND r.property=:propertyone")
    Optional<Reviews> findByUserAndProperty(@Param("appuserone") AppUser appUser,@Param("propertyone") Property property);

    @Query("SELECT r FROM Reviews r WHERE r.appUser=:appuser")
    List<Reviews> findByUser(@Param("appuser") AppUser appUser);
}