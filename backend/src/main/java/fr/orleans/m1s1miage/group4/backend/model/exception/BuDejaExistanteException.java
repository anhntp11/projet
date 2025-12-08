package fr.orleans.m1s1miage.group4.backend.model.exception;

public class BuDejaExistanteException extends RuntimeException {
    public BuDejaExistanteException() {
        super("Cette BU existe deja.");
    }
}
