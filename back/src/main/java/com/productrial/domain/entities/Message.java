package com.productrial.domain.entities;

import com.productrial.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Message extends BaseProductTrial {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "message", nullable = false)
    private String message;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.setCreatedAt(now);
        this.setUpdatedAt(now);
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }
}
