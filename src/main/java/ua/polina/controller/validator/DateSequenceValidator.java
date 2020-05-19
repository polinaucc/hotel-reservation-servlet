package ua.polina.controller.validator;

import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class DateSequenceValidator {
    public void validate(HttpServletRequest request, String field1, String field2) throws DateException {
        LocalDate startDate = LocalDate.parse(field1);
        LocalDate endDate = LocalDate.parse(field2);

        if(endDate.isBefore(startDate)) throw new DateException("End date must be after start date");
    }
}
