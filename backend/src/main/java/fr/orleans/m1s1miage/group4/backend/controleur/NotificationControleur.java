package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.motiification.NotificationDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationControleur {

    private final NotificationService notificationService;

    public NotificationControleur(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/etudiant/{idEtu}/notification")
    public ResponseEntity<List<NotificationDTO>> getNotifEtudiant(
            @PathVariable Long idEtu
    ){
        List<NotificationDTO> notificationDTOS = notificationService.getAllNotificationsForEtudiant(idEtu);
        return ResponseEntity.status(HttpStatus.OK).body(notificationDTOS);
    }


    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationDTO>> getAllNotif() {
        List<NotificationDTO> notificationDTOS = notificationService.getAllNotifications();
        return ResponseEntity.status(HttpStatus.OK).body(notificationDTOS);
    }
}
