package ua.polina.controller.command.admin;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Reservation;
import ua.polina.model.entity.Room;
import ua.polina.model.entity.Status;
import ua.polina.model.service.RequestService;
import ua.polina.model.service.ReservationService;
import ua.polina.model.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindRoomCommand implements Command {
    private final RequestService requestService;
    private final RoomService roomService;
    private final ReservationService reservationService;

    public FindRoomCommand(RequestService requestService, RoomService roomService, ReservationService reservationService) {
        this.requestService = requestService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }


    @Override
    public String execute(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession();
        long requestId = Long.parseLong(servletRequest.getParameter("id"));
        System.out.println("Request id: " + requestId);
        session.setAttribute("request_id", requestId);
        Request request = requestService.getRequestById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("No such request"));
        List<Room> rooms = roomService.getRoomsByDescription(request.getDescription());

        List<Room> wrongRooms = new ArrayList<>();

        List<Reservation> reservations = reservationService.getAllReservations();

        for (Room room : rooms) {
            for (Reservation res : reservations) {
                if(res.getRoom().equals(room) &&
                        (request.getCheckInDate().compareTo(res.getRequest().getCheckInDate()) >= 0 &&
                                request.getCheckInDate().isBefore(res.getRequest().getCheckOutDate())) ||
                        (request.getCheckOutDate().compareTo(res.getRequest().getCheckInDate()) >= 0 &&
                                request.getCheckOutDate().compareTo(res.getRequest().getCheckOutDate()) <= 0)){
                    wrongRooms.add(room);
                    break;
                }
            }
        }
        for(Room r: wrongRooms){
            rooms.remove(r);
        }
        servletRequest.setAttribute("rooms", rooms);
        servletRequest.setAttribute("request", request);

        if (rooms.size() == 0) requestService.update(request, Status.Rejected);
        else requestService.update(request, Status.Accepted);

        return "/find-room.jsp";
    }
}
