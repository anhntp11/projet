package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.motiification.NotificationDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.entity.Notification;
import fr.orleans.m1s1miage.group4.backend.model.exception.EtudiantInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository repo;
    private final EtudiantService  etudiantService;

    public NotificationService(NotificationRepository repo,  EtudiantService etudiantService) {
        this.repo = repo;
        this.etudiantService = etudiantService;
    }

    /**
     * Récupère toutes les Notifications d'un étudiant donné
     * @param idEtu id de l'étudiant
     * @return Une liste des Notifications sous forme de DTO
     * @throws EtudiantInconnuException si l'id de l'étudiant n'existe pas
     */
    public List<NotificationDTO> getAllNotificationsForEtudiant(Long idEtu)
            throws EtudiantInconnuException {
        Etudiant etu = etudiantService.getEtudiant(idEtu);

        return repo.findNotificationByEtudiant(etu).stream()
                .map(NotificationDTO::new)
                .toList();
    }

    /**
     * Récupère toutes les notifications de tous les Etudiants
     * @return Une liste des Notifications sous forme de DTO
     */
    public List<NotificationDTO> getAllNotifications(){
        return repo.findAll().stream()
                .map(NotificationDTO::new)
                .toList();
    }
}
