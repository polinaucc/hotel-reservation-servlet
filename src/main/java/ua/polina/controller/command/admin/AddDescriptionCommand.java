package ua.polina.controller.command.admin;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.model.dto.DescriptionDto;
import ua.polina.model.entity.RoomType;
import ua.polina.model.service.DescriptionService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddDescriptionCommand extends MultipleMethodCommand {
    private final DescriptionService descriptionService;

    public AddDescriptionCommand(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "/add-description-page.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        DescriptionDto descriptionDto = new DescriptionDto();
        descriptionDto.setRoomType(RoomType.valueOf(request.getParameter("roomType")));
        descriptionDto.setCountOfPersons(Integer.parseInt(request.getParameter("persons")));
        descriptionDto.setCountOfBeds(Integer.parseInt(request.getParameter("beds")));
        descriptionDto.setCostPerNight(new BigDecimal(request.getParameter("costPerNight")));
        descriptionService.saveNewDescription(descriptionDto);
        return "/ok.jsp";
    }
}
