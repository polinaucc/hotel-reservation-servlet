package ua.polina.controller.command.admin;

import ua.polina.controller.command.Command;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Reservation;
import ua.polina.model.service.RequestService;
import ua.polina.model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class GetReservationInfoCommand implements Command {
    private final RequestService requestService;
    private final ReservationService reservationService;

    public GetReservationInfoCommand(RequestService requestService, ReservationService reservationService) {
        this.requestService = requestService;
        this.reservationService = reservationService;
    }

    @Override
    public String execute(HttpServletRequest servletRequest) {
        Long requestId = Long.parseLong(servletRequest.getParameter("id"));
        Request request = requestService.getRequestById(requestId)
                .orElseThrow(()->new IllegalArgumentException("No request with such id"));
        Reservation reservation = reservationService.getReservationByRequest(request)
                .orElseThrow(()->new IllegalArgumentException("No reservation by such request"));
        servletRequest.setAttribute("reservation", reservation);
        return "/reservation-info.jsp";
    }
}
