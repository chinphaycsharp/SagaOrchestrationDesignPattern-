package com.example.order_service_ms.controller;

import com.example.order_service_ms.dto.OrderRequestDTO;
import com.example.order_service_ms.dto.OrderResponseDTO;
import com.example.order_service_ms.entity.PurchaseOrder;
import com.example.order_service_ms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Mono<PurchaseOrder> createOrder(@RequestBody Mono<OrderRequestDTO> orderMono) {
        return orderMono.flatMap(orderService::createOrder);
    }

    @GetMapping("/all")
    public Flux<OrderResponseDTO> getOrders() {
        return orderService.getAllOrder();
    }

}