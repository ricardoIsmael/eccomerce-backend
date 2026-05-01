package com.ecommerce.order_service.service;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;

import java.util.List;

public interface OrderService
{
    OrderResponse createOrder(OrderRequest orderRequest,String userId);
    //List<OrderResponse> getAllOrder();
    List<OrderResponse> getOrder(String userId, boolean isAdmin);
    OrderResponse getOrderById(Long id);
    void deleteOrder(Long id);
}
