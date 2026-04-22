package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.dto.ProductoRequestDTO;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //crud estandar httpstatus si es pdf responsentity ademas que en tes es mas facil
    public ProductResponseDTO createProduct(@RequestBody @Valid ProductoRequestDTO productoRequestDTO){
        return productService.creatProduct(productoRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> listProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable  String id ){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductoById(@PathVariable String id){
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO updateProductById(@PathVariable String id,
                                                @RequestBody @Valid ProductoRequestDTO productoRequestDTO){
        return productService.updateProduct(id,productoRequestDTO);
    }

    @GetMapping("/test-tail")
    public void testFail(){
        throw new RuntimeException("Boom la basde de datos exploto simulacro");
    }


}
