package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")  // ✅ Changed to PLURAL for REST standard
@CrossOrigin(origins = "*")  // ✅ CORS enabled for testing
public class VehicleController {

    @Autowired
    private VehicleDAO vehicleDAO;

    // ✅ Register a new vehicle
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle savedVehicle = vehicleDAO.save(vehicle);
            return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get vehicle by license plate
    @GetMapping("/{plate}")
    public ResponseEntity<Vehicle> getVehicleByPlate(@PathVariable String plate) {
        Vehicle vehicle = vehicleDAO.findByLicensePlate(plate);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ✅ Get all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleDAO.findAll();
            
            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Delete vehicle by license plate
    @DeleteMapping("/{plate}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable String plate) {
        try {
            vehicleDAO.deleteByLicensePlate(plate);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(
            "Vehicle API is running! Time: " + new Date(), 
            HttpStatus.OK
        );
    }
}