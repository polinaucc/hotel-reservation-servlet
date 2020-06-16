package ua.polina.controller.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum CompositeValidator {
    EMAIL(new NotBlankValidator(),
            new RangeValidator(6, 40),
            new FormatValidator("^(.+)@(.+)$")),
    USERNAME(new NotBlankValidator(),
            new RangeValidator(5, 20)),
    PASSWORD(new NotBlankValidator(),
            new RangeValidator(5, 20)),
    FIRST_NAME(new NotBlankValidator(),
            new FormatValidator("^[A-Z][a-z]{4,19}")),
    FIRST_NAME_UK(new NotBlankValidator(),
            new FormatValidator("^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{3,19}$")),
    MIDDLE_NAME(new NotBlankValidator(),
            new FormatValidator("^[A-Z][a-z]{4,19}")),
    MIDDLE_NAME_UK(new NotBlankValidator(),
            new FormatValidator("(^[А-ЩЇЮЯІ][а-щїюяіь']{2,19})(ович$|івна$|ївна$)")),
    LAST_NAME(new NotBlankValidator(),
            new FormatValidator("^[A-Z][a-z]{4,19}")),
    LAST_NAME_UK(new NotBlankValidator(),
            new FormatValidator("^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{4,20}$")),
    PASSPORT(new NotBlankValidator(),
            new FormatValidator("[А-Я]{2}[0-9]{6}$")),
    BIRTHDAY(new DateValidator(Option.IS_PAST));

    private List<Validator> validators;

    CompositeValidator(Validator... validators) {
        this.validators = Arrays.asList(validators);
    }

    public Optional<List<String>> validate(HttpServletRequest request, String field) {
        List<String> messages = new ArrayList<>();

        for (Validator validator :
                validators) {
            try {
                validator.validate(request, field);
            } catch (Exception e) {
                messages.add(e.getMessage());
            }
        }
        if (messages.size() > 0) {
            return Optional.of(messages);
        } else
            return Optional.empty();
    }
}
