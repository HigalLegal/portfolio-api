package dev.higormorais.utils;

public class IntegerNumberOperations {

    public static int toPrimitive(Integer value) {
        return value == null ? 0 : value.intValue();
    }

    public static int idealLimitReturn(int limit, int offset, int max) {
        return offset >= limit ? max : limit;
    }

}
