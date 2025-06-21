package com.productrial.domain.entities;

import com.productrial.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product extends BaseProductTrial {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String descrption;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "internalReference", nullable = false)
    private String internalReference;

    @Column(name = "shellId", nullable = false)
    private Integer shellId;

    @Column(name = "inventoryStatus", nullable = false)
    private InventoryStatus inventoryStatus;

    @Column(name = "rating", nullable = false)
    private Integer rating;
}
