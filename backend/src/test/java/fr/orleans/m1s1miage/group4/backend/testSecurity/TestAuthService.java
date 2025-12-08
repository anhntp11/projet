package fr.orleans.m1s1miage.group4.backend.testSecurity;

import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.JwtDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.LoginDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.exception.ConnexionImpossibleException;
import fr.orleans.m1s1miage.group4.backend.model.service.EtudiantService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TestAuthService {
    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserDetailsService userDetailsService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtService jwtService;

    @Mock
    EtudiantService etudiantService;

    @InjectMocks
    AuthService authService;

    //etudiant login success => JwtDto + token
    @Test
    void TestLoginSuccess(){
        LoginDTO login = new LoginDTO("user@test-etu.univ-orlean.fr", "user123");

        UserDetails userDetails = User.withUsername("user@test-etu.univ-orleans.fr")
                        .password("encoded")
                        .roles("ETUDIANT")
                        .build();

        when(userDetailsService.loadUserByUsername("user@test-etu.univ-orlean.fr")).thenReturn(userDetails);
        when(jwtService.generateToken(any())).thenReturn("jwt-token");

        JwtDTO jwtDTO = authService.login(login);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("jwt-token", jwtDTO.getToken());
    }

    //login fail => Connexion impossible Exception
    @Test
    void TestLoginFail(){
        LoginDTO login = new LoginDTO("user@test-etu.univ-orlean.fr", "wrong");

        doThrow(new AuthenticationException("Bad credentials") {})
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(ConnexionImpossibleException.class,
                () -> authService.login(login));
        verifyNoInteractions(userDetailsService, jwtService);
    }
    //etudiant inscription => creer etudiant + token
//    @Test
//    void testInscriptionEtudiant(){
//        EtudiantRegisterDTO etuRegis = new EtudiantRegisterDTO("newUser@test-etu.univ-orleans.fr",
//                "password", "Nom", "Prenom", "o2251123", "UFR");
//
//        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
//        when(jwtService.generateToken(any())).thenReturn("jwt-inscription");
//
//        JwtDTO jwt = authService.inscription(etuRegis);
//        verify(etudiantService).save(any(Etudiant.class));
//        verify(jwtService).generateToken(any(UserDetails.class));
//        assertEquals("jwt-inscription", jwt.getToken());
//    }
    //

}
