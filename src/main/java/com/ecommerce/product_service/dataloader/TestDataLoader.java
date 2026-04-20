package com.ecommerce.product_service.dataloader;

import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component  //cuando spring inciliza creaa un objeto de esta clase y lo guarda si no lo ponemos no ve la clase y por la tanto no lo inyecta
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product = Product.builder()
                .name("Sansung")
                .description("Smartphone")
                .price(BigDecimal.valueOf(200))
                .build();
        productoRepository.save(product);
        System.out.println("Datos de prueba cargados: " + product.getName());
    }
}
