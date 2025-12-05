package fr.orleans.m1s1miage.group4.backend.model.dto.motiification;

import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.entity.Notification;

public class NotificationDTO {
    private Long idNotification;
    private String message;
    private Long etudiantId; // ID de l'étudiant associé

    // Constructeur par défaut
    public NotificationDTO() {}

    // Constructeur complet
    public NotificationDTO(Long idNotification, String message, Long etudiantId) {
        this.idNotification = idNotification;
        this.message = message;
        this.etudiantId = etudiantId;
    }

    public NotificationDTO(Notification notification) {
        this.idNotification = notification.getId();
        this.message = notification.getMessage();
        this.etudiantId = notification.getEtudiant().getIdEtudiant();
    }

    // Getters et setters
    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }
}
