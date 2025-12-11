package fr.orleans.m1s1miage.group4.backend.testControleur;

import fr.orleans.m1s1miage.group4.backend.controleur.CatalogueController;
import fr.orleans.m1s1miage.group4.backend.model.dto.catalogue.CatalogueDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatalogueController.class)
@AutoConfigureMockMvc(addFilters = false)

public class TestCatalogControleur {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LivreService livreService;

    @Test
    void testRechercherLivresCatalogue() throws Exception {
        // GIVEN
        LivreDTO livre1 = new LivreDTO();
        livre1.setIdLivre(1L);
        livre1.setTitre("Livre 1");

        LivreDTO livre2 = new LivreDTO();
        livre2.setIdLivre(2L);
        livre2.setTitre("Livre 2");

        when(livreService.rechercherLivres(null, null, null))
                .thenReturn(List.of(livre1, livre2));

        mockMvc.perform(get("/api/catalogue/filtre")
                        .param("genre", "Roman")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titre").value("Livre 1"))
                .andExpect(jsonPath("$[1].titre").value("Livre 2"));
    }
    @Test
    void testGetCatalogue() throws Exception {
        // GIVEN
        LivreDTO livre1 = new LivreDTO();
        livre1.setIdLivre(1L);
        livre1.setTitre("Livre A");

        LivreDTO livre2 = new LivreDTO();
        livre2.setIdLivre(2L);
        livre2.setTitre("Livre B");

        CatalogueDTO catalogueDTO = new CatalogueDTO(List.of(livre1, livre2));

        when(livreService.getCatalogue()).thenReturn(catalogueDTO);

        mockMvc.perform(get("/api/catalogue")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.livres.length()").value(2))
                .andExpect(jsonPath("$.livres[0].titre").value("Livre A"))
                .andExpect(jsonPath("$.livres[1].titre").value("Livre B"));
    }
}
