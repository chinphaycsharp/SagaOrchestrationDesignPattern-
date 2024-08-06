package com.example.inventory_service_ms.controller;

import com.example.inventory_service_ms.dto.InventoryRequestDTO;
import com.example.inventory_service_ms.dto.InventoryResponeDTO;
import com.example.inventory_service_ms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("inventory")
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/deduct")
    public InventoryResponeDTO deduct(@RequestBody InventoryRequestDTO rqRequestDTO)
    {
        return inventoryService.deduct(rqRequestDTO);
    }

    @PostMapping("/add")
    public void add(@RequestBody InventoryRequestDTO rqRequestDTO)
    {
        inventoryService.add(rqRequestDTO);
    }

}
