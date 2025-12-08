package fr.orleans.m1s1miage.group4.backend.model.exception;

public class UtilisateurDejaExistantException extends RuntimeException {
    public UtilisateurDejaExistantException() {
        super("Un utilisateur avec cet Email existe déjà");
    }
}
