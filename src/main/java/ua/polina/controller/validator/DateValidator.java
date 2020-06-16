package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * @author Polina Serhiienko
 *
 * This class is used for date validation to check if it is in the past or in the future
 */
public class DateValidator implements Validator {
    /**
     * It's an option that determines the type of check: the date was in the past or should be in the future
     */
    private Option option;

    /**
     * Instantiates a new Date validator.
     *
     * @param option the option
     */
    public DateValidator(Option option) {
        this.option = option;
    }

    /**
     * check if the date was in the past and if the person is full-aged
     *
     * @param field the date from the form
     * @throws DateException date exception
     */
    private void isPastDate(String field) throws DateException {
        LocalDate todayDate = LocalDate.now();
        LocalDate date = LocalDate.parse(field, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.UK));
        if (date.isAfter(todayDate)) throw new DateException("past.date");
        if (ChronoUnit.YEARS.between(date, todayDate) < 18) throw new DateException("full.aged.person");
    }

    /**
     * check if the date should be un the future
     *
     * @param field the date from the form
     * @throws DateException date exception
     */
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
