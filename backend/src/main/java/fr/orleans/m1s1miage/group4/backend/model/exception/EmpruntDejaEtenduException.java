package fr.orleans.m1s1miage.group4.backend.model.exception;

public class EmpruntDejaEtenduException extends RuntimeException {
    public EmpruntDejaEtenduException() {
        super("L'emprunt a deja été étendu au maximum");
    }
}
