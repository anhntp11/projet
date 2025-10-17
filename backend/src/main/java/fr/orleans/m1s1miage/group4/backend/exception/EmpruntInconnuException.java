package fr.orleans.m1s1miage.group4.backend.exception;

public class EmpruntInconnuException extends RuntimeException {
    public EmpruntInconnuException() {
        super("Emprunt inconnu.");
    }
}
