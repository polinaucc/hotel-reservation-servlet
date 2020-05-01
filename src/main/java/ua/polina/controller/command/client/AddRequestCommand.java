package ua.polina.controller.command.client;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.model.dto.RequestDto;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;
import ua.polina.model.entity.User;
import ua.polina.model.service.ClientService;
import ua.polina.model.service.DescriptionService;
import ua.polina.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class AddRequestCommand extends MultipleMethodCommand {
    private final RequestService requestService;
    private final DescriptionService descriptionService;
    private final ClientService clientService;

    public AddRequestCommand(RequestService requestService, DescriptionService descriptionService, ClientService clientService) {
        this.requestService = requestService;
        this.descriptionService = descriptionService;
        this.clientService = clientService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "/add-request.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        RequestDto requestDto = new RequestDto();
        User user = (User) request.getSession().getAttribute("principals");
        Client client = clientService.getClientByUser(user)
                .orElseThrow(()->new IllegalArgumentException("No such client"));
        requestDto.setRoomType(RoomType.valueOf(request.getParameter("roomType")));
        requestDto.setCountOfPerson(Integer.parseInt(request.getParameter("persons")));
        requestDto.setCountOfBeds(Integer.parseInt(request.getParameter("beds")));
        requestDto.setCheckInDate(LocalDate.parse(request.getParameter("check_in_date")));
        requestDto.setCheckOutDate(LocalDate.parse(request.getParameter("check_out_date")));
        System.out.println(requestDto);
        Description description = descriptionService.getDescriptionByParameters(requestDto)
                .orElseThrow(()->new IllegalArgumentException("No such Description"));
        requestService.saveNewRequest(requestDto, client, description);
        return "/register-client.jsp";
    }
}
