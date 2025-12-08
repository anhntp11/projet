package fr.orleans.m1s1miage.group4.backend.model.exception;

public class LivreInconnuException extends RuntimeException {
    public LivreInconnuException() {
        super("Livre inconnu");
    }
}
