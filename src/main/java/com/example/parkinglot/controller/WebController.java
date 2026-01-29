package com.example.parkinglot.controller;

import com.example.parkinglot.dao.VehicleDAO;
import com.example.parkinglot.dao.SlotDAO;
import com.example.parkinglot.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private SlotDAO slotDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @GetMapping("/gui")
    public String gui(Model model) {
        model.addAttribute("vehicles", vehicleDAO.findAll());
        model.addAttribute("slots", slotDAO.findAll());
        model.addAttribute("transactions", transactionDAO.findAll());
        return "index";
    }
}

