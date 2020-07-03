package ua.polina.controller.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class RangeValidatorTest {
    RangeValidator rangeValidator;

    @Before
    public void setUp() throws Exception {
        rangeValidator = new RangeValidator(5, 7);
    }

    @Test
    public void validateRight() {
        Throwable thrown = catchThrowable(() -> {
            rangeValidator.validate(Mockito.any(HttpServletRequest.class), "-----");
        });
        assertThat(thrown).isNull();
    }

    @Test
    public void validateWrong() {
        Throwable thrown = catchThrowable(() -> {
            rangeValidator.validate(Mockito.any(HttpServletRequest.class), "------------");
        });
        assertThat(thrown.getMessage()).isEqualTo("out.of.range");
    }
}