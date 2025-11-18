package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

}
