package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.BUController;
import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.BUService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BUController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestBUController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BUService buService;

    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private AuthService authService;

    @Test
    void testCreateBU() throws Exception {
        BUCreationDTO creationDTO = new BUCreationDTO();
        BUDTO buDTO = new BUDTO();

        when(buService.creerBU(any(BUCreationDTO.class)))
                .thenReturn(buDTO);

        mockMvc.perform(post("/api/bu")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetBUList() throws Exception {
        when(buService.findAll())
                .thenReturn(List.of(new BUDTO(), new BUDTO()));

        mockMvc.perform(get("/api/bu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }
}
