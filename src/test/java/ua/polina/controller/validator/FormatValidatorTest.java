package ua.polina.controller.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class FormatValidatorTest {
    /**
     * validator for email
     */
    FormatValidator formatValidator;

    @Before
    public void setUp() throws Exception {
        formatValidator = new FormatValidator("^(.+)@(.+)$");
    }

    @Test
    public void validateRight() {
        Throwable thrown = catchThrowable(() -> {
            formatValidator.validate(Mockito.any(HttpServletRequest.class), "user@gmail.com");
        });
        assertThat(thrown).isNull();
    }

    @Test
    public void validateWrong() {
        Throwable thrown = catchThrowable(() -> {
            formatValidator.validate(Mockito.any(HttpServletRequest.class), "usergmail.com");
        });
        assertThat(thrown.getMessage()).isEqualTo("field.not.match.format");
    }
}