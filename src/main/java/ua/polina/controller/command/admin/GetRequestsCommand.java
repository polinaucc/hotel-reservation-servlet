package ua.polina.controller.command.admin;

import ua.polina.controller.command.Command;
import ua.polina.model.entity.Request;
import ua.polina.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetRequestsCommand implements Command {
    private final RequestService requestService;

    public GetRequestsCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
        return "/get-requests.jsp";
    }
}
