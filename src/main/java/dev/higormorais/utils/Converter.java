package dev.higormorais.utils;


import dev.higormorais.entities.Technology;

import java.time.LocalDate;

/*
* Classe utilitária para conversão simples de um tipo de dado para outro...
* */
public class Converter {

    public static String localDateToMonthString(LocalDate date) {

        int monthNumber = date.getMonthValue();

        String monthString = "janeiro";

        switch (monthNumber) {
            case 2 -> monthString = "fevereiro";
            case 3 -> monthString = "março";
            case 4 -> monthString = "abril";
            case 5 -> monthString = "maio";
            case 6 -> monthString = "junho";
            case 7 -> monthString = "julho";
            case 8 -> monthString = "agosto";
            case 9 -> monthString = "setembro";
            case 10 -> monthString = "outubro";
            case 11 -> monthString = "novembro";
            case 12 -> monthString = "dezembro";
        }

        return monthString;

    }

    public static Technology integerIdToTechnology(Integer id){
        return Technology.builder().id(id).build();
    }

    public static String technologyToString(Technology technology) {
        return technology.getName();
    }

}
