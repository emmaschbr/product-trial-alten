package com.productrial.business.services;

import com.productrial.business.constantes.MessageExceptions;
import com.productrial.business.dtos.ProductDTO;
import com.productrial.business.exceptions.NotFoundException;
import com.productrial.business.mappers.ProductMapper;
import com.productrial.domain.entities.Product;
import com.productrial.domain.repositories.ProductRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Value("${jwt.secret:monSecretKeyQuiFaitAuMoins256BitsDeSecurite}\n")
    private String secretKey;

    @Value("${jwt.expiration:86400000}") //24heures
    private long jwtExpiration;

    /**
     * Génère un token
     * @param email email d'un utilisateur
     * @return
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Obtient l'email d'un utilisateur à partir de son token
     * @param token
     * @return
     */
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * Vérifie la validité d'un token
     * @param token
     * @return
     */
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrait un claim du token
     * @param token
     * @return
     */
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Retourne la key
     * @return
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }


}
