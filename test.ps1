# Simple test
Write-Host "Testing API..." -ForegroundColor Yellow

# Health check
try {
    $health = Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles/health" -Method GET
    Write-Host "Health: $health" -ForegroundColor Green
} catch {
    Write-Host "Health check failed" -ForegroundColor Red
}

# Add cars
$cars = @("CAR1", "CAR2", "CAR3", "CAR4", "CAR5")
foreach ($car in $cars) {
    try {
        Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles?licensePlate=$car" -Method POST
        Write-Host "Added: $car" -ForegroundColor Green
    } catch {
        Write-Host "Failed: $car" -ForegroundColor Red
    }
}

# Show all
try {
    $all = Invoke-RestMethod -Uri "http://localhost:8080/api/vehicles" -Method GET
    Write-Host "
All cars:" -ForegroundColor Cyan
    $all
} catch {
    Write-Host "Can't get cars" -ForegroundColor Red
}
