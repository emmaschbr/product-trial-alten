package com.productrial.business.services;

import com.productrial.business.constantes.MessageExceptions;
import com.productrial.business.dtos.ProductDTO;
import com.productrial.business.exceptions.NotFoundException;
import com.productrial.business.mappers.ProductMapper;
import com.productrial.domain.entities.Product;
import com.productrial.domain.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    /**
     * Récupère tous les produits
     *
     * @return List des produits
     */
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        System.out.println(products);
        return ProductMapper.INSTANCE.productsToProductDTOs(products);
    }

    /**
     * Récupère un produit selon son id.
     *
     * @param productId id du produit
     * @return productDTO produit correspondant à l'id en paramètre
     * @throws BadRequestException si aucune affaire ne correspond à cet id
     */
    public ProductDTO getProduct(Integer productId) throws BadRequestException {
        if (productId == null) {
            throw new BadRequestException(MessageExceptions.PRODUCT_ID_NON_RENSEIGNE_MSG);
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(
                String.format(MessageExceptions.PRODUCT_ID_NON_EXISTANT_MSG, productId)));
        return ProductMapper.INSTANCE.productToProductDTO(product);
    }

    /**
     * Créé ou modifie un produit
     *
     * @param productId  id du produit à modifier (null si création)
     * @param productDTO contenu du produit
     * @return id du produit créé ou modifié
     */
    public Integer createOrUpdateProduct(Integer productId, ProductDTO productDTO) {
        ProductDTO productCreateUpdateDTO = new ProductDTO();
        productCreateUpdateDTO = productDTO;
        if (productId != null) {
            productCreateUpdateDTO.setId(productId);
        }
        // TODO: Method to check validity of product creation and edition
        Product product = ProductMapper.INSTANCE.productDTOToProduct(productCreateUpdateDTO);
        return productRepository.save(product).getId();
    }

    /**
     * Supprime un produit
     *
     * @param productId id du produit à supprimer
     * @throws NotFoundException si aucun produit de correspond à cet id
     */
    public void deleteProduct(Integer productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format(MessageExceptions.PRODUCT_ID_NON_EXISTANT_MSG, productId));
        }
    }
}
