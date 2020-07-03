package ua.polina.controller.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(JUnit4.class)
public class DateValidatorTest {
    DateValidator dateValidator1;
    DateValidator dateValidator2;

    @Before
    public void setUp() throws Exception {
        dateValidator1 = new DateValidator(Option.IS_FUTURE);
        dateValidator2 = new DateValidator(Option.IS_PAST);
    }

    @Test
    public void validateIsFuture() {
        Throwable thrown = catchThrowable(() -> {
            dateValidator1.validate(Mockito.any(HttpServletRequest.class), "1998-10-25");
        });
        assertThat(thrown.getMessage()).isEqualTo("future.date");

        Throwable thrown2 = catchThrowable(() -> {
            dateValidator1.validate(Mockito.any(HttpServletRequest.class), "19908-10-25");
        });
        assertThat(thrown2.getMessage()).isEqualTo("wrong.date.format");
    }

    @Test
    public void validateFutureRightDate() {
        Throwable thrown = catchThrowable(() -> {
            dateValidator1.validate(Mockito.any(HttpServletRequest.class), "2020-10-25");
        });
        assertThat(thrown).isNull();
    }

    @Test
    public void validatePastRightDate() {
        Throwable thrown = catchThrowable(() -> {
            dateValidator2.validate(Mockito.any(HttpServletRequest.class), "1999-10-25");
        });
        assertThat(thrown).isNull();
    }

    @Test
    public void validateIsPast() {
        Throwable thrown = catchThrowable(() -> {
            dateValidator2.validate(Mockito.any(HttpServletRequest.class), "2020-10-25");
        });
        assertThat(thrown.getMessage()).isEqualTo("past.date");

        Throwable thrown2 = catchThrowable(() -> {
            dateValidator2.validate(Mockito.any(HttpServletRequest.class), "2018-10-25");
        });
        assertThat(thrown2.getMessage()).isEqualTo("full.aged.person");
    }

}