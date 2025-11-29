package fr.orleans.m1s1miage.group4.backend.model.exception;

public class ConnexionImpossibleException extends RuntimeException {
    public ConnexionImpossibleException() {
        super("Email ou mot de passe incorrect");
    }
}
