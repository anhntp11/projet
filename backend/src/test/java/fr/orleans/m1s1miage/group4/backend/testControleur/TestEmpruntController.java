package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.EmpruntControleur;
import fr.orleans.m1s1miage.group4.backend.model.dto.emprunt.EmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.EmpruntService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmpruntControleur.class)
@AutoConfigureMockMvc(addFilters = false)
class TestEmpruntControleur {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private EmpruntService empruntService;


    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthService authService;

    @Test
    void testEmprunterLivre() throws Exception {
        Long idLivre = 1L;
        EmpruntDTO empruntDTO = new EmpruntDTO();

        when(empruntService.emprunterLivre(eq(idLivre), any(String.class)))
                .thenReturn(empruntDTO);

        mockMvc.perform(post("/api/livres/{idLivre}/emprunt", idLivre))
                .andExpect(status().isCreated());
    }
}
