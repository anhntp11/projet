package fr.orleans.m1s1miage.group4.backend.model.exception;

public class DemandeInconnueException extends RuntimeException {
    public DemandeInconnueException() {
        super("Demande inconnue");
    }
}
