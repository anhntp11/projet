package fr.orleans.m1s1miage.group4.backend.model.exception;

public class LivrePlusEnStockException extends RuntimeException {
    public LivrePlusEnStockException() {
        super("Le livre n'est plus en stock");
    }
}
