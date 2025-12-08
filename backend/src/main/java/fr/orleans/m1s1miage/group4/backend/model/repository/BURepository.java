package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BURepository extends JpaRepository<BU, Long> {
    BU findByNom(String nom);
}
