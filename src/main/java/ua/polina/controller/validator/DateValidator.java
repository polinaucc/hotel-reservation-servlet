package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateValidator implements Validator {
    private Option option;

    public DateValidator(Option option) {
        this.option = option;
    }

    private void isPastDate(String field) throws DateException {
        LocalDate todayDate = LocalDate.now();
        LocalDate date = LocalDate.parse(field, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK));
        if (date.isAfter(todayDate)) throw new DateException("past.date");
        if (ChronoUnit.YEARS.between(date, todayDate) < 18) throw new DateException("full.aged.person");
    }

    private void isFutureDate(String field) throws DateException {
        LocalDate todayDate = LocalDate.now();
        LocalDate date = LocalDate.parse(field, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK));
        if(date.isBefore(todayDate)) throw new DateException("future.date");
    }

    @Override
    public void validate(HttpServletRequest request, String field) throws DateException {
        if(option==Option.IS_PAST) isPastDate(field);
        if(option==Option.IS_FUTURE) isFutureDate(field);
    }
}
