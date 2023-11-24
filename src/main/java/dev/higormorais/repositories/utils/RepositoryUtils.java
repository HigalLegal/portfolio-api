package dev.higormorais.repositories.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.EntityNotFoundException;


public class RepositoryUtils {

    public static <T> List<T> insertPagination(PanacheQuery<T> datas, Integer page, Integer size) {

        int totalSize = (int) datas.count();

        if (page == null && size != null) {
            return datas.page(0, size).list();
        } else if (page != null && size == null) {
            return datas.page(page, totalSize).list();
        } else if (page == null && size == null) {
            return datas.list();
        }

        return datas.page(page, size).list();
    }

    public static Map<String, Parameters> createQueryUpdate(String className,
            List<String> attributes,
            List<String> attributesQuery,
            List<Object> values,
            boolean insertNullValue) {

        trowException(attributes, values);

        String queryAttributeClass = String.valueOf(className.toLowerCase().charAt(0));

        String jpql = generateJPQL(className, queryAttributeClass, attributes, attributesQuery,
                values, insertNullValue);

        Map<String, Parameters> queryMap = new HashMap<>();

        queryMap.put(jpql, generateParameters(attributesQuery, values, insertNullValue));

        return queryMap;
    }

    private static void trowException(List<String> attributes, List<Object> values) {
        int idPosition = values.size() - 1;

        if(values.get(idPosition) == null) {
            throw new EntityNotFoundException("Registro não encontrado.");
        }

    }

    private static String generateJPQL(
            String className,
            String queryAttributeClass,
            List<String> attributes,
            List<String> attributesQuery,
            List<Object> values,
            boolean insertNullValue) {

        final String jpql = "UPDATE ".concat(className + " ")
                .concat(queryAttributeClass).concat(" SET ");

        StringBuilder queryJPQL = new StringBuilder(jpql);

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == null && !insertNullValue) {
                continue;
            }

            String initQuery = ", ";

            if (i == 0) {
                initQuery = "";
            }

            if (attributes.get(i).equalsIgnoreCase("id")) {
                queryJPQL.append(" WHERE " + queryAttributeClass + "." + attributes.get(i) + " = ");
                queryJPQL.append(":".concat(attributesQuery.get(i)));
                continue;
            }

            queryJPQL.append(initQuery + queryAttributeClass.concat("." + attributes.get(i) + " = "));
            queryJPQL.append(":".concat(attributesQuery.get(i)));
        }

        return queryJPQL.toString();
    }

    private static Parameters generateParameters(List<String> attributes,
            List<Object> values,
            boolean insertNullValue) {
        Parameters parameters = new Parameters();

        for(int i = 0; i < attributes.size(); i++) {

            if(values.get(i) == null && !insertNullValue) {
                continue;
            }

            parameters.and(attributes.get(i), values.get(i));
        }

        return parameters;
    }

}
