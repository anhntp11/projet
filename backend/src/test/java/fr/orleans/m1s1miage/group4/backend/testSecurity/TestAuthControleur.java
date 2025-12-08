package fr.orleans.m1s1miage.group4.backend.testSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
//import fr.orleans.m1s1miage.group4.backend.DisableJpaAuditingConfig;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.AuthControleur;
import fr.orleans.m1s1miage.group4.backend.controleur.GlobalExceptionHandler;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.JwtDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.LoginDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import fr.orleans.m1s1miage.group4.backend.model.exception.ConnexionImpossibleException;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
//@AutoConfigureMockMvc
@AutoConfigureMockMvc(addFilters = false)
public class TestAuthControleur {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtService jwtService;

    @Test
    void TestConnexionOK() throws Exception {
        LoginDTO login = new LoginDTO("user@test", "password");
        when(authService.login(any())).thenReturn(new JwtDTO("jwt-token"));

        mockMvc.perform(post("/api/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }
}