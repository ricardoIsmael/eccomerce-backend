package com.ecommerce.order_service.event;


import java.util.List;
//evento de pedido realizado atomicidad y cohesion
public record OrderPlacedEvent(
        String orderNumber,
        String email,
        List<OrderItemsEvenet> items
) {
    public record OrderItemsEvenet(
            String sku,
            String price,
            Integer quantity
    ){

    }
}
