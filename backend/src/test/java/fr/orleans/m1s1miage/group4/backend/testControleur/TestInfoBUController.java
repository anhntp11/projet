package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.infoBUControleur;
import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBuDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.InfoBUService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = infoBUControleur.class)
@AutoConfigureMockMvc(addFilters = false)
class TestInfoBUControleur {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private InfoBUService infoBUService;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private JwtService jwtService;
    @MockitoBean
    private AuthService authService;

    @Test
    void testGetInfoBU() throws Exception {
        Long idBU = 1L;
        InfoBuDTO dto = new InfoBuDTO();

        when(infoBUService.getInfoBUDtoByBUId(idBU)).thenReturn(dto);

        mockMvc.perform(get("/api/infos")
                        .param("idBU", idBU.toString()))
                .andExpect(status().isOk());
    }


    @Test
    void testCreateInfoBU() throws Exception {
        InfoBUCreationDTO creationDTO = new InfoBUCreationDTO();
        InfoBuDTO resultDTO = new InfoBuDTO();

        when(infoBUService.createInfoBU(any(InfoBUCreationDTO.class)))
                .thenReturn(resultDTO);

        mockMvc.perform(post("/api/infos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(creationDTO)))
                .andExpect(status().isCreated());
    }


    @Test
    void testUpdateInfoBU() throws Exception {
        Long id = 10L;
        InfoBUCreationDTO editDTO = new InfoBUCreationDTO();
        InfoBuDTO updatedDTO = new InfoBuDTO();

        when(infoBUService.editInfo(eq(id), any(InfoBUCreationDTO.class)))
                .thenReturn(updatedDTO);

        mockMvc.perform(patch("/api/infos/{id}", id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(status().isOk());
    }
}
