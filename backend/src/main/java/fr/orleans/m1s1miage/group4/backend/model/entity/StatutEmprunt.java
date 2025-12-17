package fr.orleans.m1s1miage.group4.backend.model.entity;

public enum StatutEmprunt {
    EMPRUNTER,      // Le livre est emprunter
    RENDU,         // Le livre à été rendu
    EST_RENOUVELLE,// L'emprunt à été renouveller (valable une seul fois)
    REJETER,
    VALIDER,
    EN_ATTENTE
}
