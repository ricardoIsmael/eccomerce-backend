package com.ecommerce.product_service.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductResponseDTO(
        //Aplicar validaciones
        String name,
        String description,
        BigDecimal price
) {
}
