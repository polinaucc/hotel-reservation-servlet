package ua.polina.controller.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.polina.model.exception.DateException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.*;

public class DateSequenceValidatorTest {

    DateSequenceValidator dateSequenceValidator;

    @Before
    public void setUp() throws Exception {
        dateSequenceValidator = new DateSequenceValidator();
    }

    @Test
    public void validate() throws DateException {
        Throwable thrown = catchThrowable(() -> {
            dateSequenceValidator.validate(LocalDate.parse("2016-05-07"), LocalDate.parse("2017-05-09"));
        });
        assertThat(thrown).isNull();

        Throwable thrown2 = catchThrowable(() -> {
            dateSequenceValidator.validate(LocalDate.parse("2019-05-07"), LocalDate.parse("2017-05-09"));
        });
        assertThat(thrown2.getMessage()).isEqualTo("wrong.sequence.date");
    }
}