package com.example.order_service_ms.repo;
import java.util.UUID;

import com.example.order_service_ms.entity.PurchaseOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrderRespository extends ReactiveCrudRepository<PurchaseOrder, UUID> {

}