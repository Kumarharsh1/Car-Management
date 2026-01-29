package com.example.parkinglot.controller;

import com.example.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingServiceController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public ResponseEntity<String> parkVehicle(@RequestParam String plate) {
        try {
            String result = parkingService.parkVehicle(plate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/unpark")
    public ResponseEntity<String> unparkVehicle(@RequestParam String plate) {
        try {
            String result = parkingService.unparkVehicle(plate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
