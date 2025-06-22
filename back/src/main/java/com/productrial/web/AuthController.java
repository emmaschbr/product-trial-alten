package com.productrial.web;

import com.productrial.business.dtos.AuthenticationDTO;
import com.productrial.business.dtos.LoginRequestDTO;
import com.productrial.business.dtos.ProductDTO;
import com.productrial.business.dtos.TokenResponseDTO;
import com.productrial.business.services.AuthService;
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
public class AuthController {

    private AuthService authService;

    @PostMapping(value="/account")
    @Operation(summary="Créer un nouveau compte utilisateur")
    public ResponseEntity<Void> register(@RequestBody AuthenticationDTO request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    @Operation(summary = "Se connecter et obtenir un token JWT")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO request) {
        TokenResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

}
