package com.ecommerce.inventory_service.event;


import java.util.List;

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
