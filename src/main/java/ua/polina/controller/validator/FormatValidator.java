package ua.polina.controller.validator;

import ua.polina.model.exception.FormatException;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class FormatValidator implements Validator {
    private  String regularExpression;

    public FormatValidator(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    @Override
    public void validate(HttpServletRequest request, String field) throws Exception {
        if(!Pattern.matches(regularExpression, field))
            throw new FormatException("field.not.match.format");
    }
}
