package com.henil.personalProject.airbnbApp.controller;

import com.henil.personalProject.airbnbApp.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
}
