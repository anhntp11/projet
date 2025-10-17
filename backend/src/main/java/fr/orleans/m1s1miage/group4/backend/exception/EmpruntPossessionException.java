package fr.orleans.m1s1miage.group4.backend.exception;

public class EmpruntPossessionException extends RuntimeException {
  public EmpruntPossessionException() { super("L'emprunt n'appartient pas a l'utilisateur"); }
}
