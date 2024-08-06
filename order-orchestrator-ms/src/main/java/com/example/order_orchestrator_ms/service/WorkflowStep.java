package com.example.order_orchestrator_ms.service;

import reactor.core.publisher.Mono;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();

    Mono<Boolean> process();

    Mono<Boolean> revert();

}