package dev.higormorais.utils;

import io.quarkus.panache.common.Parameters;

import java.util.Map;

public class GeneratedJPQL {

    public static String addQuery(String urlImage, String aliasClass) {

        if(urlImage == null) {
            return "";
        }

        return " " + aliasClass.concat(".urlImage = :urlImage,");
    }

    public static Parameters excludeImageIfNull(Map<String, Object> parametersMap, String urlImageValue) {
        Map<String, Object> parametersValue = excludeImageIfNullMap(parametersMap, urlImageValue);

        return mapToParameters(parametersValue);
    }

    private static Map<String, Object> excludeImageIfNullMap(Map<String, Object> parameters, String urlImageValue) {
        if(urlImageValue != null && !urlImageValue.isBlank()) {
            return parameters;
        }

        parameters.remove("urlImage");

        return parameters;

    }

    public static Parameters mapToParameters(Map<String, Object> parametersMap) {
        Parameters parameters = new Parameters();

        for (Map.Entry<String, Object> entry : parametersMap.entrySet()) {
            parameters = parameters.and(entry.getKey(), entry.getValue());
        }

        return parameters;
    }

}
