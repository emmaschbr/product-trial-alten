package com.productrial.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur extends BaseProductTrial {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "username", nullable = false)
    private String username;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


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
