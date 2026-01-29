package com.example.parkinglot.dao;

import com.example.parkinglot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle, Long> {
    Vehicle findByLicensePlate(String plate);
}
