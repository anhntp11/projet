package fr.orleans.m1s1miage.group4.backend.model.exception;

public class GenreInconnuException extends RuntimeException {
    public GenreInconnuException() {
        super("Genre inconnu.");
    }
}
