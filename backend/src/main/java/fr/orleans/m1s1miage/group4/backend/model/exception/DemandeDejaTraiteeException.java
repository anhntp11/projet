package fr.orleans.m1s1miage.group4.backend.model.exception;

public class DemandeDejaTraiteeException extends RuntimeException {
    public DemandeDejaTraiteeException() {
        super("La demande à déjà été traitée");
    }
}
