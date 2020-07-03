package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * This class is used for date validation to check if it is in
 * the past or in the future
 *
 * @author Polina Serhiienko
 */
public class DateValidator implements Validator {
    /**
     * It's an option field that determines the type of check:
     * the date was in the past or should be in the future
     */
    private Option option;

    /**
     * Constructor, instantiates a new Date validator.
     *
     * @param option the option
     */
    public DateValidator(Option option) {
        this.option = option;
    }

    /**
     * Method checks if the date was in the past and if the person is full-aged
     *
     * @param field the date from the form
     * @throws DateException date exception
     */
    private void isPastDate(String field) throws DateException {
        try {
            LocalDate todayDate = LocalDate.now();
            LocalDate date = LocalDate.parse(field, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK));
            if (date.isAfter(todayDate)) throw new DateException("past.date");
            if (ChronoUnit.YEARS.between(date, todayDate) < 18) throw new DateException("full.aged.person");
        } catch (DateTimeParseException e) {
            throw new DateException("wrong.date.format");
        }
    }

    /**
     * Method checks if the date should be in the future
     *
     * @param field the date from the form
     * @throws DateException date exception
     */
    private void isFutureDate(String field) throws DateException {
        try {
            LocalDate todayDate = LocalDate.now();
            LocalDate date = LocalDate.parse(field, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK));
            if (date.isBefore(todayDate)) throw new DateException("future.date");
        } catch (DateTimeParseException e) {
            throw new DateException("wrong.date.format");
        }
    }

    @Override
    public void validate(HttpServletRequest request, String field) throws DateException {
        if (option == Option.IS_PAST) isPastDate(field);
        if (option == Option.IS_FUTURE) isFutureDate(field);
    }
}
