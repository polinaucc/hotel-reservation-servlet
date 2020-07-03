package ua.polina.controller.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

public class NotBlankValidatorTest {

    NotBlankValidator notBlankValidator;

    @Before
    public void setUp() throws Exception {
        notBlankValidator = new NotBlankValidator();

    }

    @Test
    public void validate() {
        Throwable thrown = catchThrowable(() -> {
            notBlankValidator.validate(Mockito.any(HttpServletRequest.class), "");
        });
        assertThat(thrown.getMessage()).isEqualTo("blank.field");
    }

    @Test
    public void validateNotBlankField() {
        Throwable thrown = catchThrowable(() -> {
            notBlankValidator.validate(Mockito.any(HttpServletRequest.class), "Not blank");
        });
        assertThat(thrown).isNull();
    }
}