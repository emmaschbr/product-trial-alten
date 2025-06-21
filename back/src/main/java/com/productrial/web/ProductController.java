package com.productrial.web;

import com.productrial.business.dtos.ProductDTO;
import com.productrial.business.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController extends ExceptionController {

    private ProductService productService;

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère tous les produits")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère un produit selon son id")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer productId) throws BadRequestException {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping(value = "/products")
    @Operation(summary = "Création d'un produit")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request)
            throws URISyntaxException {
        //TODO: make create and edit dtos ?
        Integer idCreated = productService.createOrUpdateProduct(null, productDTO);
        return ResponseEntity.created(new URI(request.getRequestURI() + "/" + idCreated)).build();
    }

    @PatchMapping(value = "/products/{productId}")
    @Operation(summary = "Modification d'un produit selon son id")
    public ResponseEntity<Void> UpdateProduct(@PathVariable Integer productId,
                                                @RequestBody ProductDTO productDTO) {
        productService.createOrUpdateProduct(productId, productDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Suppression d'une affaire selon son id")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }



}
