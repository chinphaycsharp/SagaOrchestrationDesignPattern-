package com.example.order_orchestrator_ms.steps;

import com.example.order_orchestrator_ms.common.PaymentRequestDTO;
import com.example.order_orchestrator_ms.common.PaymentResponseDTO;
import com.example.order_orchestrator_ms.common.PaymentStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


import reactor.core.publisher.Mono;
import com.example.order_orchestrator_ms.service.WorkflowStep;
import com.example.order_orchestrator_ms.service.WorkflowStepStatus;

public class PaymentStep implements WorkflowStep {

    private final WebClient webClient;
    private final PaymentRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(WebClient webClient, PaymentRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        return webClient
                .post()
                .uri("/payment/debit")
                .body(BodyInserters.fromValue(requestDTO))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class)
                .map(r -> r.getStatus().equals(PaymentStatus.PAYMENT_APPROVED))
                .doOnNext(b -> stepStatus = b ? WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                .post()
                .uri("/payment/credit")
                .body(BodyInserters.fromValue(requestDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .map(r -> true)
                .onErrorReturn(false);
    }

}
