package com.example.inventory_service_ms.service;

import com.example.inventory_service_ms.dto.InventoryRequestDTO;
import com.example.inventory_service_ms.dto.InventoryResponeDTO;
import com.example.inventory_service_ms.dto.InventoryStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    public Map<Integer, Integer> inventoryMap;

    @PostConstruct
    private  void init()
    {
        inventoryMap = new HashMap<>();
        inventoryMap.put(1, 200);
        inventoryMap.put(2, 100);
        inventoryMap.put(3, 400);
    }

    public InventoryResponeDTO deduct(InventoryRequestDTO requestDTO)
    {
        int qty = inventoryMap.getOrDefault(requestDTO.getProductId(),0);

        InventoryResponeDTO responseDTO = new InventoryResponeDTO();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
        if(qty > 0)
        {
            responseDTO.setStatus(InventoryStatus.AVAILABLE);
            inventoryMap.put(requestDTO.getProductId(),qty - 1);
        }

        return responseDTO;
    }

    public void add(InventoryRequestDTO requestDTO)
    {
        inventoryMap.computeIfPresent(requestDTO.getProductId(), (k,v)-> v + 1);
    }
}

