package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}
