package fr.orleans.m1s1miage.group4.backend.exception;

public class DateEcheanceEmpruntDepasseeException extends RuntimeException {
    public DateEcheanceEmpruntDepasseeException() {
        super("La date d'échéance de l'emprunt à été dépassée");
    }
}
