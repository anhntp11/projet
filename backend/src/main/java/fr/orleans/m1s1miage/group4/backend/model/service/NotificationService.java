package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Notification;
import fr.orleans.m1s1miage.group4.backend.model.repository.NotificationRepository;

import java.util.List;

public class NotificationService {
    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les genres sauvegardés en BD
     */
    public List<Notification> findAll() {
        return repo.findAll();
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @param notification La notification a sauvegarder
     */
    public void Save(Notification notification) {
        repo.save(notification);
    }

}
