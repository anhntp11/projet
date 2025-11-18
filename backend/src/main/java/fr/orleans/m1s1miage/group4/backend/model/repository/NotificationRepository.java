package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
