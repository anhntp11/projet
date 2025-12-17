package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.JwtDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.LoginDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.exception.ConnexionImpossibleException;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthControleur {

    private final AuthService authService;

    public AuthControleur(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Permet de se connecter, en récupérant un JWT
     * @param loginDTO le DTO des informations de connexion
     * @return un String du JWT
     */
    @PostMapping("/connexion")
    public ResponseEntity<JwtDTO> connexion(
            @RequestBody LoginDTO loginDTO
            ) {
        JwtDTO jwt = authService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }

    /**
     * Permet de s'inscrire, et génère un JWT
     * @param etudiantRegisterDTO Un DTO des infos de création du compte
     * @return un String du JWT
     */
    @PostMapping("/inscription")
    public ResponseEntity<JwtDTO> inscription(
            @RequestBody EtudiantRegisterDTO etudiantRegisterDTO
            ) {
        JwtDTO jwt = authService.insciption(etudiantRegisterDTO);
        return ResponseEntity.status(HttpStatus.OK).body(jwt);
    }
}