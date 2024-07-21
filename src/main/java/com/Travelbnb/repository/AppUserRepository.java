package com.Travelbnb.repository;

import com.Travelbnb.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    //Finder methods

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<AppUser> findByUsername(String username);
}