import heapq

class ParkingAllocator:
    def __init__(self, slots):
        self.available = slots  # list of (distance, slot_id)
        heapq.heapify(self.available)

    def allocate(self):
        if self.available:
            return heapq.heappop(self.available)
        return None

    def release(self, slot):
        heapq.heappush(self.available, slot)

if __name__ == "__main__":
    allocator = ParkingAllocator([(1, "SlotA"), (2, "SlotB"), (3, "SlotC")])
    print("Allocated:", allocator.allocate())
    print("Allocated:", allocator.allocate())
    allocator.release((2, "SlotB"))
    print("Available slots:", allocator.available)