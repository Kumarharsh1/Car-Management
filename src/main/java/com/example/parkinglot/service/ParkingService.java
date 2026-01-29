package com.example.parkinglot.service;

import com.example.parkinglot.dao.SlotDAO;
import com.example.parkinglot.dao.TransactionDAO;
import com.example.parkinglot.model.Slot;
import com.example.parkinglot.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;

@Service
public class ParkingService {
    
    @Autowired
    private SlotDAO slotDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    public String parkVehicle(String plate) {
        // Find available slot
        Optional<Slot> availableSlot = slotDAO.findAll().stream()
                .filter(s -> !s.isOccupied())
                .findFirst();
        
        if (!availableSlot.isPresent()) {
            return "No available slots!";
        }
        
        Slot slot = availableSlot.get();
        slot.setOccupied(true);
        slot.setVehiclePlate(plate);
        slotDAO.save(slot);
        
        // Create transaction
        Transaction txn = new Transaction();
        txn.setVehiclePlate(plate);
        txn.setEntryTime(LocalDateTime.now());
        txn.setExitTime(null);
        txn.setCharges(0.0);
        transactionDAO.save(txn);
        
        return "Vehicle " + plate + " parked in Slot " + slot.getSlotNumber();
    }

    public String unparkVehicle(String plate) {
        // Find slot with this vehicle
        Optional<Slot> slotOpt = slotDAO.findAll().stream()
                .filter(s -> s.isOccupied() && plate.equals(s.getVehiclePlate()))
                .findFirst();
        
        if (!slotOpt.isPresent()) {
            return "Vehicle not found or not parked!";
        }
        
        // Free the slot
        Slot slot = slotOpt.get();
        slot.setOccupied(false);
        slot.setVehiclePlate(null);
        slotDAO.save(slot);
        
        // Update transaction
        Optional<Transaction> txnOpt = transactionDAO.findByVehiclePlate(plate).stream()
                .filter(t -> t.getExitTime() == null)
                .findFirst();
        
        if (txnOpt.isPresent()) {
            Transaction txn = txnOpt.get();
            txn.setExitTime(LocalDateTime.now());
            
            // Calculate charges: ₹50 per hour
            long minutes = Duration.between(txn.getEntryTime(), txn.getExitTime()).toMinutes();
            double hours = Math.ceil(minutes / 60.0);
            double charges = hours * 50.0;
            txn.setCharges(charges);
            
            transactionDAO.save(txn);
        }
        
        return "Vehicle " + plate + " unparked from Slot " + slot.getSlotNumber();
    }
}
