package com.example.parkinglot.config;

import com.example.parkinglot.dao.SlotDAO;
import com.example.parkinglot.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SlotDAO slotDAO;

    @Override
    public void run(String... args) throws Exception {
        // Check if slots already exist
        if (slotDAO.count() == 0) {
            // Initialize 10 parking slots
            for (int i = 1; i <= 10; i++) {
                Slot slot = new Slot(i, false, null);
                slotDAO.save(slot);
            }
        }
    }
}
