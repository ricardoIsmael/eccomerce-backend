package com.ecommerce.order_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest
{
    @NotEmpty(message = "La orden debe contener al menos un item")
    @Valid //como no tenemos un controlador para validar itemsrquest con valid hacemos esto
    private List<OrderLineItemsRequest> orderLineItemsList;
}
