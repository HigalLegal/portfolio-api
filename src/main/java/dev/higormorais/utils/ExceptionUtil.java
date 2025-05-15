package dev.higormorais.utils;

import dev.higormorais.exceptions.ImageException;
import jakarta.persistence.EntityNotFoundException;

import java.io.File;

public class ExceptionUtil {

    public static void throwExceptionNotFound(int number, String messageError) {
        if(number <= 0) {
            throw new EntityNotFoundException(messageError);
        }
    }

    public static void throwImageException(File image, String messageError) {
        if(image == null) {
            throw new ImageException(messageError);
        }
    }

}
