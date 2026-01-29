package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleDAO vehicleDAO;

    // ✅ Accept JSON body instead of query param
    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleDAO.save(vehicle);
    }

    @GetMapping("/{plate}")
    public Vehicle getVehicleByPlate(@PathVariable String plate) {
        return vehicleDAO.findByLicensePlate(plate);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }

    @GetMapping("/health")
    public String health() {
        return "Backend is running! Time: " + new Date();
    }
}

