package com.example.parkinglot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Slot {
    @Id
    private Integer slotNumber;
    private boolean occupied;
    private String vehiclePlate;

    public Slot() {}

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
        this.occupied = false;
        this.vehiclePlate = null;
    }

    public Slot(Integer slotNumber, boolean occupied, String vehiclePlate) {
        this.slotNumber = slotNumber;
        this.occupied = occupied;
        this.vehiclePlate = vehiclePlate;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }
}
