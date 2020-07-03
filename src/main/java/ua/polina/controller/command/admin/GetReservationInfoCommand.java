package ua.polina.controller.command.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.controller.command.Command;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Reservation;
import ua.polina.model.service.RequestService;
import ua.polina.model.service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetReservationInfoCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetReservationInfoCommand.class);
    private final RequestService requestService;
    private final ReservationService reservationService;
    private ResourceBundle rb;

    public GetReservationInfoCommand(RequestService requestService, ReservationService reservationService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));
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
        } catch (IllegalArgumentException e) {
            LOGGER.warn(rb.getString(e.getMessage()));
            servletRequest.setAttribute("smthError", e.getMessage());
            return "/error.jsp";
        }
    }
}
