package dev.higormorais.utils;

import jakarta.persistence.EntityNotFoundException;

public class ExceptionUtil {

    public static void throwExceptionNotFound(int number, String messageError) {
        if(number <= 0) {
            throw new EntityNotFoundException(messageError);
        }
    }

}
