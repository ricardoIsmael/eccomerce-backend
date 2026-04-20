package com.ecommerce.product_service.mapper;

import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.dto.ProductoRequestDTO;
import com.ecommerce.product_service.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") //permite inyectar con autowirred
public interface ProuctoMapper {
    @Mapping(target = "id",ignore = true)
    Product toProduct(ProductoRequestDTO productoRequestDTO); //tenemos una requese y lo tranformammos a producto
    //conversiones de tipo
    ProductResponseDTO toProductResponseDTO(Product product);//tebnenos un producto y lo tranformamos a response
    @Mapping(target = "id", ignore = true)
    void updateProductFromRequest(ProductoRequestDTO productoRequestDTO, @MappingTarget Product product); // copiamos los datos de la request y lo enviasmos al producto
}
