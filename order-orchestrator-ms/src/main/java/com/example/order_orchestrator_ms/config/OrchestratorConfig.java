package com.example.order_orchestrator_ms.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import reactor.core.publisher.Flux;
import com.example.order_orchestrator_ms.common.OrchestratorRequestDTO;
import com.example.order_orchestrator_ms.common.OrchestratorResponseDTO;
import com.example.order_orchestrator_ms.service.OrchestratorService;

@Configuration
public class OrchestratorConfig {

    @Autowired
    private OrchestratorService orchestratorService;

    @Bean
    public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processor() {
        return flux -> flux.flatMap(dto -> orchestratorService.orderProduct(dto))
                .doOnNext(dto -> System.out.println("Status : " + dto.getStatus()));
    }

}