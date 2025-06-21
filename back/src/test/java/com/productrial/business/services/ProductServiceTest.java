package com.productrial.business.services;

import com.productrial.business.dtos.ProductDTO;
import com.productrial.business.exceptions.NotFoundException;
import com.productrial.business.mappers.ProductMapper;
import com.productrial.domain.entities.Product;
import com.productrial.domain.repositories.ProductRepository;
import com.productrial.enums.InventoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires pour ProductService")
public class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;
    private ProductDTO testProductDTO;

    @BeforeEach
    void setUp() {
        testProduct = createTestProduct();
        testProductDTO = createTestProductDTO();
    }

    private Product createTestProduct() {
        Product product = new Product();
        product.setId(1);
        product.setCode("P001");
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setImage("test.jpg");
        product.setCategory("Test Category");
        product.setPrice(99.99);
        product.setQuantity(10);
        product.setInternalReference("REF001");
        product.setShellId(1);
        product.setInventoryStatus(InventoryStatus.INSTOCK);
        product.setRating(5);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    private ProductDTO createTestProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1);
        dto.setCode("P001");
        dto.setName("Test Product");
        dto.setDescription("Test Description");
        dto.setImage("test.jpg");
        dto.setCategory("Test Category");
        dto.setPrice(99.99);
        dto.setQuantity(10);
        dto.setInternalReference("REF001");
        dto.setShellId(1);
        dto.setInventoryStatus(InventoryStatus.INSTOCK);
        dto.setRating(5);
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedAt(LocalDateTime.now());
        return dto;
    }

    @Test
    void testGetAllProducts() {
        //Given TODO
        List<Product> products = Arrays.asList(testProduct, createTestProduct());
        List<ProductDTO> productDTOs = Arrays.asList(testProductDTO, createTestProductDTO());

        when(productRepository.findAll()).thenReturn(products);

        //When
        List<ProductDTO> result = productService.getAll();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        //TODO
    }

    @Test void testGetProductInvalidId() {
        //Given
        Integer productId = 876;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productService.getProduct(productId));
        verify(productRepository).findById(productId);
    }

    //TODO: testGetProductValidId
    // testCreateProductValidId
    // testCreateProductInvalidId
    // testUpdateProductValidId
    // testUpdateProductInvalidId
    // testDeleteProductValidId
    // testDeleteProductInvalidId

}
