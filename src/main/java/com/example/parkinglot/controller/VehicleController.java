package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleDAO vehicleDAO;
    
    @PostMapping(params = "licensePlate")
    public String addVehicle(@RequestParam String licensePlate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(licensePlate);
        vehicleDAO.save(vehicle);
        return "Added: " + licensePlate;
    }
    
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }
    
    @GetMapping("/health")
    public String health() {
        return "Backend API is working!";
    }
}
