package com.productrial.business.services;


import com.productrial.business.constantes.MessageExceptions;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilisateurService {

    /**
     * Vérifie si le user a les droits d'admin
     * @param authentication
     */
    public void hasAdminPrivileve(Authentication authentication) {
        String  userEmail = authentication.getDeclaringClass().getName();
        if (!"admin@admin.com".equals(userEmail)) {
            throw new RuntimeException(MessageExceptions.USER_NON_ADMIN);
        }
    }
}
