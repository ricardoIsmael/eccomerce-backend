package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.dto.ProductoRequestDTO;
import com.ecommerce.product_service.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponseDTO creatProduct(ProductoRequestDTO requestDTO);
    List<ProductResponseDTO> getAllProduct();
    ProductResponseDTO getProductById(String id);
    ProductResponseDTO updateProduct(String id, ProductoRequestDTO productoRequestDTO);
    void deleteProduct(String id);
}
