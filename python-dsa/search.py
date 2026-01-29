class VehicleSearch:
    def __init__(self):
        self.vehicles = {}

    def park(self, vehicle_id, slot):
        self.vehicles[vehicle_id] = slot

    def find(self, vehicle_id):
        return self.vehicles.get(vehicle_id, "Not Found")

if __name__ == "__main__":
    search = VehicleSearch()
    search.park("CAR123", "SlotA")
    search.park("BIKE456", "SlotB")
    print("CAR123 parked at:", search.find("CAR123"))
    print("TRUCK789 parked at:", search.find("TRUCK789"))