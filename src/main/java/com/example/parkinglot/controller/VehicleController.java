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
    
    @GetMapping("/")
    public String home() {
        long vehicleCount = vehicleDAO.count();
        
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>🚗 Parking Lot Management API</title>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        max-width: 1000px;
                        margin: 0 auto;
                        padding: 30px;
                        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
                        min-height: 100vh;
                    }
                    
                    .container {
                        background: white;
                        border-radius: 15px;
                        padding: 40px;
                        box-shadow: 0 10px 30px rgba(0,0,0,0.1);
                    }
                    
                    h1 {
                        color: #1e3c72;
                        text-align: center;
                        margin-bottom: 10px;
                        font-size: 2.5rem;
                    }
                    
                    .subtitle {
                        text-align: center;
                        color: #666;
                        margin-bottom: 40px;
                        font-size: 1.1rem;
                    }
                    
                    .status-card {
                        background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
                        color: white;
                        padding: 25px;
                        border-radius: 10px;
                        text-align: center;
                        margin-bottom: 30px;
                    }
                    
                    .status-card h2 {
                        margin-top: 0;
                        font-size: 1.8rem;
                    }
                    
                    .stats {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                        gap: 20px;
                        margin-bottom: 40px;
                    }
                    
                    .stat-box {
                        background: #f8f9fa;
                        padding: 20px;
                        border-radius: 10px;
                        text-align: center;
                        border-left: 4px solid #2a5298;
                    }
                    
                    .stat-number {
                        font-size: 2.5rem;
                        font-weight: bold;
                        color: #1e3c72;
                        margin: 10px 0;
                    }
                    
                    .endpoints {
                        background: #f8f9fa;
                        padding: 25px;
                        border-radius: 10px;
                        margin-bottom: 30px;
                    }
                    
                    .endpoint {
                        background: white;
                        padding: 15px;
                        border-radius: 8px;
                        margin: 10px 0;
                        border-left: 4px solid #4CAF50;
                        font-family: 'Courier New', monospace;
                    }
                    
                    .method {
                        display: inline-block;
                        padding: 5px 12px;
                        border-radius: 4px;
                        font-weight: bold;
                        margin-right: 10px;
                        font-size: 0.9rem;
                    }
                    
                    .method.get { background: #4CAF50; color: white; }
                    .method.post { background: #2196F3; color: white; }
                    
                    .test-buttons {
                        display: flex;
                        gap: 15px;
                        justify-content: center;
                        flex-wrap: wrap;
                        margin: 30px 0;
                    }
                    
                    .test-btn {
                        padding: 12px 25px;
                        border: none;
                        border-radius: 8px;
                        font-weight: bold;
                        cursor: pointer;
                        font-size: 1rem;
                        transition: all 0.3s;
                    }
                    
                    .test-btn:hover {
                        transform: translateY(-3px);
                        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
                    }
                    
                    .btn-health { background: #4CAF50; color: white; }
                    .btn-add { background: #2196F3; color: white; }
                    .btn-list { background: #FF9800; color: white; }
                    
                    .result-box {
                        background: #e8f5e9;
                        padding: 20px;
                        border-radius: 10px;
                        margin-top: 20px;
                        display: none;
                        font-family: monospace;
                        white-space: pre-wrap;
                        word-break: break-all;
                    }
                    
                    .footer {
                        text-align: center;
                        margin-top: 40px;
                        color: #666;
                        font-size: 0.9rem;
                        border-top: 1px solid #eee;
                        padding-top: 20px;
                    }
                    
                    .url {
                        background: #f0f0f0;
                        padding: 8px 15px;
                        border-radius: 5px;
                        font-family: monospace;
                        margin: 5px;
                        display: inline-block;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🚗 Parking Lot Management API</h1>
                    <p class="subtitle">Spring Boot Backend with H2 Database</p>
                    
                    <div class="status-card">
                        <h2>✅ API is Running Successfully!</h2>
                        <p>Your backend is live and accepting requests</p>
                    </div>
                    
                    <div class="stats">
                        <div class="stat-box">
                            <div>Total Vehicles</div>
                            <div class="stat-number" id="vehicleCount">""" + vehicleCount + """</div>
                            <div>Registered in database</div>
                        </div>
                        <div class="stat-box">
                            <div>API Status</div>
                            <div class="stat-number">✅ LIVE</div>
                            <div>All endpoints active</div>
                        </div>
                        <div class="stat-box">
                            <div>Database</div>
                            <div class="stat-number">H2</div>
                            <div>In-memory storage</div>
                        </div>
                    </div>
                    
                    <div class="endpoints">
                        <h3>📡 Available API Endpoints</h3>
                        <div class="endpoint">
                            <span class="method get">GET</span>
                            <a href="/api/vehicles/health" style="color: #1e3c72; text-decoration: none;">
                                /api/vehicles/health
                            </a>
                            <div style="color: #666; margin-top: 5px;">Health check endpoint</div>
                        </div>
                        
                        <div class="endpoint">
                            <span class="method get">GET</span>
                            <a href="/api/vehicles" style="color: #1e3c72; text-decoration: none;">
                                /api/vehicles
                            </a>
                            <div style="color: #666; margin-top: 5px;">List all registered vehicles</div>
                        </div>
                        
                        <div class="endpoint">
                            <span class="method post">POST</span>
                            <span style="color: #1e3c72;">/api/vehicles?licensePlate=XXX</span>
                            <div style="color: #666; margin-top: 5px;">Add a new vehicle (replace XXX with plate)</div>
                        </div>
                    </div>
                    
                    <div class="test-buttons">
                        <button class="test-btn btn-health" onclick="testHealth()">Test Health Endpoint</button>
                        <button class="test-btn btn-add" onclick="testAddVehicle()">Add Test Vehicle</button>
                        <button class="test-btn btn-list" onclick="testListVehicles()">List All Vehicles</button>
                    </div>
                    
                    <div id="result" class="result-box"></div>
                    
                    <div class="footer">
                        <p><strong>Backend URL:</strong> 
                            <span class="url">https://car-management-2-n35v.onrender.com</span>
                        </p>
                        <p><strong>Frontend:</strong> Deploy index.html on Vercel for full parking system UI</p>
                        <p><strong>Database:</strong> H2 in-memory (resets on restart) | <strong>Total Vehicles:</strong> <span id="liveCount">""" + vehicleCount + """</span></p>
                        <p style="margin-top: 20px;">
                            <button onclick="location.reload()" style="padding: 8px 20px; background: #6c757d; color: white; border: none; border-radius: 5px; cursor: pointer;">
                                ↻ Refresh Page
                            </button>
                        </p>
                    </div>
                </div>
                
                <script>
                    function testHealth() {
                        showLoading('Testing health endpoint...');
                        fetch('/api/vehicles/health')
                            .then(response => response.text())
                            .then(data => {
                                showResult('✅ Health Check Response:\\n' + data);
                                updateVehicleCount();
                            })
                            .catch(error => {
                                showResult('❌ Error: ' + error);
                            });
                    }
                    
                    function testAddVehicle() {
                        const randomPlate = 'TEST' + Math.floor(Math.random() * 10000);
                        showLoading('Adding vehicle: ' + randomPlate + '...');
                        
                        fetch('/api/vehicles?licensePlate=' + encodeURIComponent(randomPlate), {
                            method: 'POST'
                        })
                        .then(response => response.text())
                        .then(data => {
                            showResult('✅ Vehicle Added:\\n' + data);
                            updateVehicleCount();
                        })
                        .catch(error => {
                            showResult('❌ Error: ' + error);
                        });
                    }
                    
                    function testListVehicles() {
                        showLoading('Fetching all vehicles...');
                        fetch('/api/vehicles')
                            .then(response => response.json())
                            .then(data => {
                                if (data.length === 0) {
                                    showResult('📭 No vehicles found in database');
                                } else {
                                    let result = '📋 Registered Vehicles (' + data.length + '):\\n\\n';
                                    data.forEach((vehicle, index) => {
                                        result += (index + 1) + '. ' + vehicle.licensePlate + '\\n';
                                    });
                                    showResult(result);
                                }
                                updateVehicleCount();
                            })
                            .catch(error => {
                                showResult('❌ Error: ' + error);
                            });
                    }
                    
                    function updateVehicleCount() {
                        fetch('/api/vehicles')
                            .then(response => response.json())
                            .then(data => {
                                document.getElementById('vehicleCount').textContent = data.length;
                                document.getElementById('liveCount').textContent = data.length;
                            });
                    }
                    
                    function showLoading(message) {
                        document.getElementById('result').innerHTML = '⏳ ' + message;
                        document.getElementById('result').style.display = 'block';
                        document.getElementById('result').style.background = '#fff3cd';
                    }
                    
                    function showResult(message) {
                        document.getElementById('result').innerHTML = message;
                        document.getElementById('result').style.display = 'block';
                        document.getElementById('result').style.background = message.includes('✅') ? '#d4edda' : 
                                                                           message.includes('❌') ? '#f8d7da' : '#d1ecf1';
                    }
                    
                    // Auto-refresh vehicle count every 10 seconds
                    setInterval(updateVehicleCount, 10000);
                </script>
            </body>
            </html>
            """;
    }
    
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
