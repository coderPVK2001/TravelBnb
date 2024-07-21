package com.Travelbnb.repository;

import com.Travelbnb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}