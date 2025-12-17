package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.CatalogueController;
import fr.orleans.m1s1miage.group4.backend.model.dto.catalogue.CatalogueDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CatalogueController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestCatalogueController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private LivreService livreService;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private JwtService jwtService;
    @MockitoBean
    private AuthService authService;
    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;

    @Test
    void testGetCatalogue() throws Exception {
        CatalogueDTO catalogueDTO = new CatalogueDTO();
        when(livreService.getCatalogue()).thenReturn(catalogueDTO);

        mockMvc.perform(get("/api/catalogue"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRechercherLivresAvecFiltres() throws Exception {
        LivreDTO livre1 = new LivreDTO();
        LivreDTO livre2 = new LivreDTO();

        when(livreService.rechercherLivres("Java", "A", "Informatique"))
                .thenReturn(List.of(livre1, livre2));

        mockMvc.perform(get("/api/catalogue/filtre")
                        .param("titre", "Java")
                        .param("auteur", "A")
                        .param("genre", "Informatique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testRechercherLivresSansFiltres() throws Exception {
        when(livreService.rechercherLivres(null, null, null))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/catalogue/filtre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));
    }
}
