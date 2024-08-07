package com.example.order_orchestrator_ms.common;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryResponseDTO {
    private UUID orderId;
    private Integer userId;
    private Integer productId;
    private InventoryStatus status;
}