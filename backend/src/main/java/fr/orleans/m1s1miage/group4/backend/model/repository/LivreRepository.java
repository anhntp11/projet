package fr.orleans.m1s1miage.group4.backend.model.repository;

import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre,Long> {
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);

     @Query("SELECT l FROM Livre l JOIN l.genres g WHERE g.name = :nomGenre")
        List<Livre> findByGenre(@Param("nomGenre") String nomGenre);



}
