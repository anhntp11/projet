package fr.orleans.m1s1miage.group4.backend.testControleur;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.LivreControleur;
import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = LivreControleur.class,
        excludeAutoConfiguration = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                JpaRepositoriesAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
class TestLivresController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private LivreService livreService;
    @MockitoBean
    private AuthService authService;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private JwtService jwtService;
    @MockitoBean
    private JpaMetamodelMappingContext jpaMetamodelMappingContext;


    @Test
    void getAllLivres() throws Exception {
        when(livreService.findAll())
                .thenReturn(List.of(new LivreDTO(), new LivreDTO()));
        mockMvc.perform(get("/api/livres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void createLivre() throws Exception {
        LivreCreationDTO creationDTO = new LivreCreationDTO();

        when(livreService.createLivre(any(LivreCreationDTO.class)))
                .thenReturn(new LivreDTO());

        mockMvc.perform(post("/api/livres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creationDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void getLivreById_NotFound() throws Exception {
        when(livreService.getLivreById(999L))
                .thenThrow(new LivreInconnuException());

        mockMvc.perform(get("/api/livres/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
