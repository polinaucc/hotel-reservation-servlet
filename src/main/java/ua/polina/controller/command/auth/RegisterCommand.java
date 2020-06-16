package ua.polina.controller.command.auth;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.controller.command.utility.CommandBCryptUtility;
import ua.polina.controller.validator.CompositeValidator;
import ua.polina.model.dto.SignUpDto;
import ua.polina.model.exception.DataExistsException;
import ua.polina.model.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class RegisterCommand extends MultipleMethodCommand {
    private final ClientService clientService;
    private ResourceBundle rb;

    public RegisterCommand(ClientService clientService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        this.clientService = clientService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "/register-client.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) throws Exception {
        try {
            SignUpDto signUpDto = new SignUpDto();
            signUpDto.setEmail(request.getParameter("email"));
            signUpDto.setUsername(request.getParameter("username"));
            signUpDto.setPassword(request.getParameter("password"));
            signUpDto.setFirstName(request.getParameter("first_name"));
            signUpDto.setMiddleName(request.getParameter("middle_name"));
            signUpDto.setLastName(request.getParameter("last_name"));
            signUpDto.setPassport(request.getParameter("passport"));
            signUpDto.setBirthday(LocalDate.parse(request.getParameter("birthday")));
            Map<String, List<String>> validationMessages = validate(request, signUpDto);
            request.setAttribute("errors", validationMessages);
            if (validationMessages.isEmpty()) {
                signUpDto.setPassword(CommandBCryptUtility.encodePassword(request.getParameter("password")));
                clientService.saveNewClient(signUpDto);
                return "/ok.jsp";
            } else return "/register-client.jsp";
        } catch (DataExistsException e) {
            request.setAttribute("error", e.getMessage());
            return "/register-client.jsp";
        }
    }

    private Map<String, List<String>> validate(HttpServletRequest request, SignUpDto signUpDto) throws Exception {
        Map<String, List<String>> validationMessages = new HashMap<>();

        CompositeValidator.EMAIL.validate(request, signUpDto.getEmail()).ifPresent(messages -> {
            validationMessages.put("email", messages);
        });

        CompositeValidator.USERNAME.validate(request, signUpDto.getUsername()).ifPresent(messages -> {
            validationMessages.put("username", messages);
        });

        CompositeValidator.PASSWORD.validate(request, signUpDto.getPassword()).ifPresent(messages -> {
            validationMessages.put("password", messages);
        });

        CompositeValidator.FIRST_NAME.validate(request, signUpDto.getFirstName()).ifPresent(messages -> {
            validationMessages.put("first_name", messages);
        });

        CompositeValidator.MIDDLE_NAME.validate(request, signUpDto.getMiddleName()).ifPresent(messages -> {
            validationMessages.put("middle_name", messages);
        });

        CompositeValidator.LAST_NAME.validate(request, signUpDto.getLastName()).ifPresent(messages -> {
            validationMessages.put("last_name", messages);
        });

        CompositeValidator.PASSPORT.validate(request, signUpDto.getPassport()).ifPresent(messages -> {
            validationMessages.put("passport", messages);
        });

        CompositeValidator.BIRTHDAY.validate(request, signUpDto.getBirthday().toString()).ifPresent(messages -> {
            validationMessages.put("birthday", messages);
        });
        return validationMessages;
    }
}
