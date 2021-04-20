package com.example.TACS2021UTN.functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAnalizer {

    public static LocalDate transformStringToLocalDate(String dateString){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateString, dateFormatter);
    }

    public static boolean validateOrderOfDatesInserted(LocalDate dateFrom, LocalDate dateTo){
        return dateTo.isAfter(dateFrom);
    }

}
