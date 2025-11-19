package fr.orleans.m1s1miage.group4.backend.model.exception;

public class BuDejaExistateException extends RuntimeException {
    public BuDejaExistateException() {
        super("Cette BU existe deja.");
    }
}
