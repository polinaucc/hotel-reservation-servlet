package ua.polina.controller.command.admin;

import ua.polina.controller.command.Command;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Room;
import ua.polina.model.service.RequestService;
import ua.polina.model.service.ReservationService;
import ua.polina.model.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddReservationCommand implements Command {
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final RequestService requestService;

    public AddReservationCommand(RoomService roomService, ReservationService reservationService, RequestService requestService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest servletRequest) {
        try {
            HttpSession session = servletRequest.getSession();
            Long roomId = Long.parseLong(servletRequest.getParameter("room_id"));
            Room room = roomService.getRoomById(roomId)
                    .orElseThrow(() -> new IllegalArgumentException("No such room"));
            Long requestId = (Long) (session.getAttribute("request_id"));
            Request request = requestService.getRequestById(requestId)
                    .orElseThrow(() -> new IllegalArgumentException("no.request.with.id"));
            reservationService.saveReservation(request, room);
            return "redirect:/get-requests";
        }
        catch (IllegalArgumentException ie){
            servletRequest.setAttribute("smthError", ie.getMessage());
            return "/error.jsp";
        }
    }
}
