package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.DemandeControleur;
import fr.orleans.m1s1miage.group4.backend.model.dto.demande.DemandeDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.DemandeService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DemandeControleur.class)
@AutoConfigureMockMvc(addFilters = false)
class TestDemandeControleur {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private DemandeService demandeService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthService authService;

    @Test
    void testGetDemandes() throws Exception {
        when(demandeService.getAllDemandes())
                .thenReturn(List.of(new DemandeDTO(), new DemandeDTO()));

        mockMvc.perform(get("/api/demandes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testValiderDemande() throws Exception {
        mockMvc.perform(patch("/api/demandes/{id}/validation", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testRejeterDemande() throws Exception {
        mockMvc.perform(patch("/api/demandes/{id}/rejet", 1L))
                .andExpect(status().isOk());
    }
}
