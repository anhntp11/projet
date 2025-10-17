package fr.orleans.m1s1miage.group4.backend.exception;

public class DemandeInconnueException extends RuntimeException {
    public DemandeInconnueException() {
        super("Demande inconnue");
    }
}
