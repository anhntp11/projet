package fr.orleans.m1s1miage.group4.backend.model.exception;

public class RetourImpossibleException extends RuntimeException {
    public RetourImpossibleException() {
        super("Impossible de rendre cet emprunt");
    }
}
