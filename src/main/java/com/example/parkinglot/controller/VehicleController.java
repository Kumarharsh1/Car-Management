package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import java.util.Date;
import com.example.parkinglot.model.Vehicle;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleDAO vehicleDAO;

    @PostMapping
    public Vehicle addVehicle(@RequestParam String licensePlate) {
        Vehicle v = new Vehicle(licensePlate);
        return vehicleDAO.save(v);
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

