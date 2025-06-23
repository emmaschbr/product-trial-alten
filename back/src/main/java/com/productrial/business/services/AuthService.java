package com.productrial.business.services;

import com.productrial.business.constantes.MessageExceptions;
import com.productrial.business.dtos.AuthenticationDTO;
import com.productrial.business.dtos.LoginRequestDTO;
import com.productrial.business.dtos.TokenResponseDTO;
import com.productrial.domain.entities.Utilisateur;
import com.productrial.domain.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Instancie une authentification
     *
     * @param authenticationDTO
     */
    public void register(AuthenticationDTO authenticationDTO) {
        //Vérifier si l'utilisateur existe déjà
        if (utilisateurRepository.findByEmail(authenticationDTO.getEmail()).isPresent()) {
            throw new RuntimeException(MessageExceptions.EMAIL_EXISTANT_MSG);
        }
        if (utilisateurRepository.findByUsername(authenticationDTO.getUsername()).isPresent()) {
            throw new RuntimeException(MessageExceptions.USERNAME_EXISTANT_MSG);
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(authenticationDTO.getUsername());
        utilisateur.setFirstname(authenticationDTO.getFirstName());
        utilisateur.setEmail(authenticationDTO.getEmail());
        utilisateur.setPassword(passwordEncoder.encode(authenticationDTO.getPassword()));

        utilisateurRepository.save(utilisateur);
    }

    /**
     * Connecte l'utilisateur et génère un token
     *
     * @param request les identifiants de connexion donnés
     * @return
     */
    public TokenResponseDTO login(LoginRequestDTO request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException(MessageExceptions.LOGIN_INVALIDE));

        if (!passwordEncoder.matches(request.getPassword(), utilisateur.getPassword())) {
            throw new RuntimeException(MessageExceptions.LOGIN_INVALIDE);
        }

        String token = jwtService.generateToken(utilisateur.getEmail());
        return new TokenResponseDTO(token);
    }

}
