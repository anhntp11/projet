package fr.orleans.m1s1miage.group4.backend.model.dto.utilisateur;

import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;

import java.util.Set;

public record EtudiantRegisterDTO(
        String email,
        String motDePasse,
        String nom,
        String prenom,
        String numEtudiant,
        String departement
) {}

