package fr.orleans.m1s1miage.group4.backend.model.exception;

public class AdminSuppressionException extends RuntimeException {
    public AdminSuppressionException() {
        super("Impossible de supprimer le compte administrateur");
    }
}
