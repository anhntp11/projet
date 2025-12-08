package fr.orleans.m1s1miage.group4.backend.model.exception;

public class EtudiantInconnuException extends RuntimeException {
    public EtudiantInconnuException() {
        super("Etudiant inconnu");
    }
}
