package ua.polina.controller.validator;

import ua.polina.model.exception.BlankFieldException;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class NotBlankValidator implements Validator {
    @Override
    public void validate(HttpServletRequest request, String field) throws BlankFieldException {
        if(Objects.isNull(field)||field.equals("")){
            String message = "Field is blank";
            throw new BlankFieldException(message);
        }
    }
}
