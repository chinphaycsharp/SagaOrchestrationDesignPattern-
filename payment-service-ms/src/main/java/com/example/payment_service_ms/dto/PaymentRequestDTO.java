package com.example.payment_service_ms.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class PaymentRequestDTO {
    private Integer userId;
    private UUID orderId;
    private Double amount;
}