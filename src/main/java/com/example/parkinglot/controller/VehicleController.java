package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
