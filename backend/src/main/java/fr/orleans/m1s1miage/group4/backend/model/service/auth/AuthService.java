package fr.orleans.m1s1miage.group4.backend.model.service.auth;

import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.JwtDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.LoginDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.exception.ConnexionImpossibleException;
import fr.orleans.m1s1miage.group4.backend.model.service.EtudiantService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService  jwtService;
    private final EtudiantService etudiantService;

    public AuthService(AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       EtudiantService etudiantService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.etudiantService = etudiantService;
    }

    /**
     * Gere la connexion d'un utilisateur
     * @param loginDTO Prend les informations de connexion
     * @return un DTO du JWT
     */
    public JwtDTO login(LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.motDePasse())
            );
        } catch (AuthenticationException e) {
            throw new ConnexionImpossibleException();
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());

        return new JwtDTO(jwtService.generateToken(userDetails));
    }

    /**
     * Gére l'inscription d'un nouvelle etudiant
     * @param etudiantRegisterDTO Le DTO avec les infos de l'étudiant
     * @return Un DTO du JWT de l'inscription
     */
    public JwtDTO inscyption(EtudiantRegisterDTO etudiantRegisterDTO) {
        String encodedPassword = passwordEncoder.encode(etudiantRegisterDTO.motDePasse());

        Etudiant etudiant = new Etudiant(etudiantRegisterDTO);
        etudiant.setMotDePasse(encodedPassword);

        etudiantService.save(etudiant);

        UserDetails userDetails = User.withUsername(etudiant.getEmail())
                .password(encodedPassword)
                .roles(etudiant.getRole().name())
                .build();

        return new JwtDTO(jwtService.generateToken(userDetails));
    }
}
