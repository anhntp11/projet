package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
