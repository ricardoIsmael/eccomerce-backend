package com.ecommerce.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductoRequestDTO(
        @NotBlank(message = "El nombre del prodycto no puede estar vacio")
        String name,
        String description, //opcional
        //request es el que hace el cliente como un post en un formulario las validacion nos ayuda en el desacoplamiento
        //al momento de llegar a la bd llega de forma consistente  xq no en el response xq solo mapeamos
        @NotNull
        @Positive(message = "El precio debe de ser mayor a cero")
        BigDecimal price
        ) {
}
