package com.productrial.business.dtos;

import com.productrial.enums.InventoryStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    @DecimalMin(value="0.0", inclusive = false)
    private Double price;
    @Min(value = 0)
    private Integer quantity;
    private String internalReference;
    private Integer shellId;
    private InventoryStatus inventoryStatus;
    @Min(value = 0)
    @Max(value = 5)
    private Integer rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
