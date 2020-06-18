package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import java.time.LocalDate;

public class DateSequenceValidator {
    public void validate(LocalDate startDate, LocalDate endDate) throws DateException {
        if (endDate.isBefore(startDate)) throw new DateException("wrong.sequence.date");
    }
}
