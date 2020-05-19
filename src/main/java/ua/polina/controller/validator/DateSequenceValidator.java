package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateSequenceValidator {
    public void validate(LocalDate startDate, LocalDate endDate) throws DateException {
        if(endDate.isBefore(startDate)) throw new DateException("wrong.sequence.date");
    }
}
