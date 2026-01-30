package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class VehicleController {
    
    @Autowired
    private VehicleDAO vehicleDAO;
    
    @GetMapping("/")
    public String home() {
        long vehicleCount = vehicleDAO.count();
        
        return "<!DOCTYPE html><html><head><title>Parking Lot API</title>" +
               "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
               "<style>body { font-family: Arial, sans-serif; max-width: 800px; margin: 40px auto; padding: 20px; }" +
               "h1 { color: #1e3c72; }" +
               ".endpoint { background: #f5f7fa; padding: 10px; border-radius: 5px; margin: 10px 0; font-family: monospace; }" +
               ".test-btn { background: #2a5298; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer; margin: 5px; }</style>" +
               "</head><body>" +
               "<h1>Parking Lot Management API</h1>" +
               "<p>Your Spring Boot backend is running successfully!</p>" +
               "<h3>Total Vehicles: " + vehicleCount + "</h3>" +
               "<h3>API Endpoints:</h3>" +
               "<div class=\"endpoint\">GET <a href=\"/api/vehicles/health\">/api/vehicles/health</a> - Health check</div>" +
               "<div class=\"endpoint\">GET <a href=\"/api/vehicles\">/api/vehicles</a> - List all vehicles</div>" +
               "<div class=\"endpoint\">POST /api/vehicles?licensePlate=XXX - Add vehicle</div>" +
               "<h3>Test API:</h3>" +
               "<button class=\"test-btn\" onclick=\"testHealth()\">Test Health</button>" +
               "<button class=\"test-btn\" onclick=\"testAdd()\">Add Test Vehicle</button>" +
               "<button class=\"test-btn\" onclick=\"testList()\">List Vehicles</button>" +
               "<div id=\"result\" style=\"margin-top: 20px; padding: 10px; background: #e8f5e9; border-radius: 5px; display: none;\"></div>" +
               "<script>" +
               "function testHealth() { fetch('/api/vehicles/health').then(r => r.text()).then(data => showResult('Health: ' + data)); }" +
               "function testAdd() { const plate = 'TEST' + Math.floor(Math.random() * 1000); fetch('/api/vehicles?licensePlate=' + plate, { method: 'POST' }).then(r => r.text()).then(data => showResult('Added: ' + data)); }" +
               "function testList() { fetch('/api/vehicles').then(r => r.json()).then(data => showResult('Vehicles: ' + JSON.stringify(data))); }" +
               "function showResult(text) { document.getElementById('result').innerHTML = text; document.getElementById('result').style.display = 'block'; }" +
               "</script>" +
               "<hr><p><strong>Backend URL:</strong> https://car-management-2-n35v.onrender.com</p>" +
               "</body></html>";
    }
    
    @PostMapping(value = "/api/vehicles", params = "licensePlate")
    public String addVehicle(@RequestParam String licensePlate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(licensePlate);
        vehicleDAO.save(vehicle);
        return "Added: " + licensePlate;
    }
    
    @GetMapping("/api/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.findAll();
    }
    
    @GetMapping("/api/vehicles/health")
    public String health() {
        return "Backend API is working!";
    }
}
