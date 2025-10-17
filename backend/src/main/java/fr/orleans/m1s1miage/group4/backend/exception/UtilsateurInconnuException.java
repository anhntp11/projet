package fr.orleans.m1s1miage.group4.backend.exception;

public class UtilsateurInconnuException extends RuntimeException {
    public UtilsateurInconnuException() {
        super("Aucun utilsateur trouvé");
    }
}
