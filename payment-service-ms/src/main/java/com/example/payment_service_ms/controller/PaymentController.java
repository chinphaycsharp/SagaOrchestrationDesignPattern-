package com.example.payment_service_ms.controller;

import com.example.payment_service_ms.dto.PaymentRequestDTO;
import com.example.payment_service_ms.dto.PaymentResponseDTO;
import com.example.payment_service_ms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO)
    {
        return paymentService.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO)
    {
        paymentService.credit(requestDTO);
    }
}
