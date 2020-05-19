package ua.polina.controller.command.admin;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.model.dto.DescriptionDto;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;
import ua.polina.model.exception.DataExistsException;
import ua.polina.model.service.DescriptionService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


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
        try {
            DescriptionDto descriptionDto = new DescriptionDto();
            descriptionDto.setRoomType(RoomType.valueOf(request.getParameter("roomType")));
            descriptionDto.setCountOfPersons(Integer.parseInt(request.getParameter("persons")));
            descriptionDto.setCountOfBeds(Integer.parseInt(request.getParameter("beds")));
            descriptionDto.setCostPerNight(new BigDecimal(request.getParameter("costPerNight")));
            List<Description> allDescriptions = descriptionService.getAllDescriptions();
            for (Description d : allDescriptions) {
                if (d.equals(descriptionService.formDescription(descriptionDto)))
                    throw new DataExistsException("description.exists");
            }
            descriptionService.saveNewDescription(descriptionDto);
            return "/ok.jsp";
        } catch (DataExistsException dee) {
            request.setAttribute("error", dee.getMessage());
            return "/add-description-page.jsp";
        }
    }
}
