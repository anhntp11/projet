package fr.orleans.m1s1miage.group4.backend.model.exception;

public class UtilisateurInconnuException extends RuntimeException {
    public UtilisateurInconnuException() {
        super("Aucun utilsateur trouvé");
    }
}
