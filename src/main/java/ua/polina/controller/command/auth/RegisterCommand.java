package ua.polina.controller.command.auth;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.controller.command.utility.CommandBCryptUtility;
import ua.polina.model.dto.DescriptionDto;
import ua.polina.model.dto.SignUpDto;
import ua.polina.model.entity.RoomType;
import ua.polina.model.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RegisterCommand extends MultipleMethodCommand {
    private final ClientService clientService;

    public RegisterCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "/register-client.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail(request.getParameter("email"));
        signUpDto.setUsername(request.getParameter("username"));
        signUpDto.setPassword(CommandBCryptUtility.encodePassword(request.getParameter("password")));
        signUpDto.setFirstName(request.getParameter("first_name"));
        signUpDto.setMiddleName(request.getParameter("middle_name"));
        signUpDto.setLastName(request.getParameter("last_name"));
        signUpDto.setPassport(request.getParameter("passport"));
        signUpDto.setBirthday(LocalDate.parse(request.getParameter("birthday")));
        clientService.saveNewClient(signUpDto);
        return "/ok.jsp";
    }
}
