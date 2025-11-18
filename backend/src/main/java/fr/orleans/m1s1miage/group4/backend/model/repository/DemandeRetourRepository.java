package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeRetour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRetourRepository extends JpaRepository<DemandeRetour, Long> {
}
