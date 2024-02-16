package dev.higormorais.utils;

public class Primitive {

    public static int toPrimitive(Integer value) {
        return value == null ? 0 : value.intValue();
    }

}
