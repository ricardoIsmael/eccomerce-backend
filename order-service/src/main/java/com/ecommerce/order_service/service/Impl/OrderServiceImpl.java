package com.ecommerce.order_service.service.Impl;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;
import com.ecommerce.order_service.exception.ResourceNotFoundException;
import com.ecommerce.order_service.mapper.OrderMapper;
import com.ecommerce.order_service.models.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import com.ecommerce.order_service.service.OrderService;
import com.ecommerce.order_service.service.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    //private final WebClient.Builder webClientBuilder;
    private final InventoryClient inventoryClient;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.toOrder(orderRequest);
        for (var item: order.getOrderLineItemsList()){
            String sku = item.getSku();
            Integer quantity = item.getQuantity();
            try{
               /* Boolean inStock = webClientBuilder.build().put()
                        .uri("http://localhost:8082/api/v1/inventory/reduce/" + sku,
                                uriBuilder -> uriBuilder.queryParam("quantity",quantity).build())
                        .retrieve()
                        .bodyToMono(Boolean.class)
                        .block();*/
                inventoryClient.reduceStock(sku,quantity);
            }catch (Exception e){
                log.error("Error al reducir stock para el pruducto{}:{}",sku,e.getMessage());
                throw new IllegalArgumentException("No se pudo procesar la ordern: Stock insufeciente o "  +
                        "Eror de inentario");
            }


        }
        order.setOrderNumber(UUID.randomUUID().toString());
        Order newOrder = orderRepository.save(order);
        log.info("orden guardad con exito. Id: {}",newOrder.getId());
        return orderMapper.toOrderResponse(newOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getAllOrder() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Orden","id",id)
                );
        return orderMapper.toOrderResponse(order);

    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
            if(!orderRepository.existsById(id))
                throw new ResourceNotFoundException("Orden","id",id);
            orderRepository.deleteById(id);
    }
}
