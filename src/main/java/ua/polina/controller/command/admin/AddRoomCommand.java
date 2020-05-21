package ua.polina.controller.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.model.dto.RoomDto;
import ua.polina.model.entity.Description;
import ua.polina.model.service.DescriptionService;
import ua.polina.model.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddRoomCommand extends MultipleMethodCommand {
    private  final DescriptionService descriptionService;
    private  final RoomService roomService;
    private static final Logger LOGGER = LogManager.getLogger(AddRoomCommand.class);
    private ResourceBundle rb;

    public AddRoomCommand(DescriptionService descriptionService, RoomService roomService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        this.descriptionService = descriptionService;
        this.roomService = roomService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        List<Description> descriptions = descriptionService.getAllDescriptions();
        request.setAttribute("descriptions", descriptions);
        return "/add-room.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        try {
            RoomDto roomDto = new RoomDto();
            roomDto.setDescriptionId(Long.parseLong(request.getParameter("description_id")));
            roomDto.setRoomNumber(request.getParameter("room_number"));
            Description description = descriptionService.getDescriptionById(roomDto.getDescriptionId())
                    .orElseThrow(() -> new IllegalArgumentException("no.description"));
            roomService.saveRoom(roomDto, description);
            return "/ok.jsp";
        }
        catch (IllegalArgumentException e){
            LOGGER.warn(rb.getString(e.getMessage()));
            request.setAttribute("smthError", e.getMessage());
            return "/error.jsp";
        }
    }
}
