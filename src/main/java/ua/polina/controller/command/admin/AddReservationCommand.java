package ua.polina.controller.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.controller.command.Command;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Room;
import ua.polina.model.service.RequestService;
import ua.polina.model.service.ReservationService;
import ua.polina.model.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddReservationCommand implements Command {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final RequestService requestService;
    private static final Logger LOGGER = LogManager.getLogger(AddReservationCommand.class);
    private ResourceBundle rb;

    public AddReservationCommand(RoomService roomService, ReservationService reservationService, RequestService requestService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest servletRequest) {
        try {
            HttpSession session = servletRequest.getSession();
            if (servletRequest.getParameter("room_id") != null) {
                long roomId = Long.parseLong(servletRequest.getParameter("room_id"));
                Room room = roomService.getRoomById(roomId)
                        .orElseThrow(() -> new IllegalArgumentException("no.room"));
                Long requestId = (Long) (session.getAttribute("request_id"));
                Request request = requestService.getRequestById(requestId)
                        .orElseThrow(() -> new IllegalArgumentException("no.request.with.id"));
                reservationService.saveReservation(request, room);
                return "redirect:/get-requests";
            }
            else throw new IllegalArgumentException("no.room");

        } catch (IllegalArgumentException e) {
            LOGGER.warn(rb.getString(e.getMessage()));
            servletRequest.setAttribute("smthError", e.getMessage());
            return "/error.jsp";
        }
    }
}
