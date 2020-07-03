package ua.polina.controller.validator;

import ua.polina.model.exception.RangeException;

import javax.servlet.http.HttpServletRequest;

public class RangeValidator implements Validator {
    private int minLength;
    private int maxLength;

    public RangeValidator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public void validate(HttpServletRequest request, String field) throws Exception {
        if (field.length() < minLength || field.length() > maxLength) {
            throw new RangeException("out.of.range");
        }
    }
}
