package com.example.parkinglot.dao;

import com.example.parkinglot.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotDAO extends JpaRepository<Slot, Integer> {
}
