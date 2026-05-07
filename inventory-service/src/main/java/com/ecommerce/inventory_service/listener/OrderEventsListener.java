package com.ecommerce.inventory_service.listener;

import com.ecommerce.inventory_service.event.OrderPlacedEvent;
import com.ecommerce.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderEventsListener
{
    private final InventoryService inventoryService;

    //araca un proceso en segundo plano un hilo que mantiene un conexion abierta con rabitaqm de forma permanente
    @RabbitListener(queues = "inventory-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event){
        event.items().forEach( items ->{
                try{
                 inventoryService.reduceStock(items.sku(),items.quantity());
                 log.info("Stock descotando para sku:{} - Cantidad:{}",items.sku(),items.quantity());
                }catch (Exception e) {
                    log.error("Error al descontadr stock para sku{}: {} ", items.sku(),e.getMessage());
                }
                }
        );
    }
}
