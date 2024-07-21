package com.Travelbnb.repository;

import com.Travelbnb.entity.Property;
import com.Travelbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query(value = "SELECT p FROM Property p JOIN Location l ON p.location= l.id JOIN Country c ON p.country=c.id WHERE l.name=:name OR c.name =:name ")
    List<Property> finddetails(@Param("name") String name);



}