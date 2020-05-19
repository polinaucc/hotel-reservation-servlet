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
        try {
            Long requestId = Long.parseLong(servletRequest.getParameter("id"));
            Request request = requestService.getRequestById(requestId)
                    .orElseThrow(() -> new IllegalArgumentException("no.request.with.id"));
            Reservation reservation = reservationService.getReservationByRequest(request)
                    .orElseThrow(() -> new IllegalArgumentException("no.reservation.by.request"));
            servletRequest.setAttribute("reservation", reservation);
            return "/reservation-info.jsp";
        }
        catch (IllegalArgumentException ie){
            servletRequest.setAttribute("smthError", ie.getMessage());
            return "/error.jsp";
        }
    }
}
