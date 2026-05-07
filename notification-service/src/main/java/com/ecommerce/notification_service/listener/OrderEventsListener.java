package com.ecommerce.notification_service.listener;


import com.ecommerce.notification_service.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class OrderEventsListener
{

    @RabbitListener(queues = "notification-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event){
        log.info("Evento recibido em inventario para orden: {}",event.orderNumber());
        event.items().forEach( items ->{
                try{
                    log.info("Envianado corre de confirmacion  a : {} ", event.email());

                    log.info("Correo enviado   a : {} ", event.email());
                }catch (Exception e) {
                    log.error("Error al enviar correo {}: {} ", items.sku(),e.getMessage());
                }
                }
        );
    }
}
