package com.example.payment_service_ms.service;

import com.example.payment_service_ms.dto.PaymentRequestDTO;
import com.example.payment_service_ms.dto.PaymentResponseDTO;
import com.example.payment_service_ms.dto.PaymentStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class  PaymentService {
    public Map<Integer, Double> paymentMap;

    @PostConstruct
    private  void init()
    {
        paymentMap = new HashMap<>();
        paymentMap.put(1, 500d);
        paymentMap.put(2, 1000d);
        paymentMap.put(3, 700d);
    }

    public PaymentResponseDTO debit(PaymentRequestDTO requestDTO)
    {
        double balance = paymentMap.getOrDefault(requestDTO.getUserId(),0d);

        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setStatus(PaymentStatus.PAYMENT_REJECTED);
        System.out.println("Inside Payment::"+balance);

        if (balance >= requestDTO.getAmount()) {
            responseDTO.setStatus(PaymentStatus.PAYMENT_APPROVED);
            paymentMap.put(requestDTO.getUserId(), balance - requestDTO.getAmount());
        }

        return responseDTO;
    }

    public void credit(PaymentRequestDTO requestDTO)
    {
        paymentMap.computeIfPresent(requestDTO.getUserId(), (k,v)-> v + requestDTO.getAmount());
    }
}
