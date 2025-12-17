package fr.orleans.m1s1miage.group4.backend.testControleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orleans.m1s1miage.group4.backend.configuration.CustomUserDetailsService;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtAuthenticationFilter;
import fr.orleans.m1s1miage.group4.backend.configuration.JwtService;
import fr.orleans.m1s1miage.group4.backend.controleur.NotificationControleur;
import fr.orleans.m1s1miage.group4.backend.model.dto.motiification.NotificationDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.NotificationService;
import fr.orleans.m1s1miage.group4.backend.model.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NotificationControleur.class)
@AutoConfigureMockMvc(addFilters = false)
class TestNotificationControleur {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private NotificationService notificationService;
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
    void testGetNotifEtudiant() throws Exception {
        Long idEtu = 1L;
        List<NotificationDTO> notifications =
                List.of(new NotificationDTO(), new NotificationDTO());

        when(notificationService.getAllNotificationsForEtudiant(idEtu))
                .thenReturn(notifications);

        mockMvc.perform(get("/api/etudiant/{idEtu}/notification", idEtu))
                .andExpect(status().isOk());
    }


    @Test
    void testGetAllNotifications() throws Exception {
        List<NotificationDTO> notifications =
                List.of(new NotificationDTO());

        when(notificationService.getAllNotifications())
                .thenReturn(notifications);
        mockMvc.perform(get("/api/notifications"))
                .andExpect(status().isOk());
    }
}
