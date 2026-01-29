package com.example.parkinglot.dao;

import com.example.parkinglot.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long> {
    List<Transaction> findByVehiclePlate(String vehiclePlate);
}
