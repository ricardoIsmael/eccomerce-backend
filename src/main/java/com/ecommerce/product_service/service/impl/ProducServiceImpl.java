package com.ecommerce.product_service.service.impl;

import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.dto.ProductoRequestDTO;

import com.ecommerce.product_service.exception.ResourceNotFoundException;
import com.ecommerce.product_service.mapper.ProuctoMapper;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.repository.ProductoRepository;
import com.ecommerce.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducServiceImpl implements ProductService {

    private final ProductoRepository productoRepository;
    private final ProuctoMapper productoMapper;

    @Override
    public ProductResponseDTO creatProduct(ProductoRequestDTO requestDTO) {
        Product product = productoMapper.toProduct(requestDTO); //nosotros recibimos un requeset mapeamos ese producto
        Product savedProducto = productoRepository.save(product);//guardamos ese pruducto
        return productoMapper.toProductResponseDTO(savedProducto);//y lo tranformamos en un response
    }

    @Override
    public List<ProductResponseDTO> getAllProduct()
    {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toProductResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(String id) {
        Product product = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto","id",id)
        );
        return productoMapper.toProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(String id, ProductoRequestDTO productoRequestDTO) {
        Product product = productoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Producto","id",id)
        );
        //forma tradicional product.setNombre(productoRequestDTO.name());
        productoMapper.updateProductFromRequest(productoRequestDTO,product);
        Product updateProduct = productoRepository.save(product);
        return  productoMapper.toProductResponseDTO(updateProduct);
    }

    @Override
    public void deleteProduct(String id) {
        if(!productoRepository.existsById(id))
            throw new ResourceNotFoundException("Producto","id",id);
        productoRepository.deleteById(id);
    }
}

