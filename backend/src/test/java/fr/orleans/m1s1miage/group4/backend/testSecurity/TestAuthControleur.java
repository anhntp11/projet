package fr.orleans.m1s1miage.group4.backend.testSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.JwtDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.auth.LoginDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur.EtudiantRegisterDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
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
    void testConnexion_Success() throws Exception {
        LoginDTO loginDTO = new LoginDTO("user@test.fr", "password");
        JwtDTO jwtDTO = new JwtDTO("fake-jwt-token");

        when(authService.login(any(LoginDTO.class))).thenReturn(jwtDTO);

        mockMvc.perform(post("/api/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("fake-jwt-token"));
    }


    @Test
    void testInscription_Success() throws Exception {
        EtudiantRegisterDTO registerDTO = new EtudiantRegisterDTO("user@test.fr", "password", "user","test", "123", "A");
        JwtDTO jwtDTO = new JwtDTO("new-token");

        when(authService.insciption(any(EtudiantRegisterDTO.class))).thenReturn(jwtDTO);

        mockMvc.perform(post("/api/auth/inscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("new-token"));
    }



}
