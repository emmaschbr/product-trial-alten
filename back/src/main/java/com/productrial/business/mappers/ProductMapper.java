package com.productrial.business.mappers;

import com.productrial.business.dtos.ProductDTO;
import com.productrial.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    List<ProductDTO> productsToProductDTOs(List<Product> products);
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}
