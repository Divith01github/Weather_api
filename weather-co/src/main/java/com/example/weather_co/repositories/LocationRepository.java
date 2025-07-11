package com.example.weather_co.repositories;

import com.example.weather_co.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByPincode(String pincode);
}
