package com.example.inventory_service_ms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class InventoryRequestDTO {
    private Integer userId;
    private Integer productId;
    private UUID orderId;
}
