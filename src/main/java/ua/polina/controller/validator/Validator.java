package ua.polina.controller.validator;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    void validate(HttpServletRequest request, String field) throws Exception;
}
