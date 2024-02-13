package dev.higormorais.exceptions;

public class InvalidKeyApiException extends RuntimeException {
    public InvalidKeyApiException(String message) {
        super(message);
    }
}
