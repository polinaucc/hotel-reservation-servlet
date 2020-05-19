package ua.polina.controller.command.client;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.utility.CommandSessionUtility;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.User;
import ua.polina.model.service.ClientService;
import ua.polina.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetMyRequestsCommand implements Command {
    private final RequestService requestService;
    private final ClientService clientService;

    public GetMyRequestsCommand(RequestService requestService, ClientService clientService) {
        this.requestService = requestService;
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User currentUser = CommandSessionUtility.getCurrentUser(request);
            Client client = clientService.getClientByUser(currentUser)
                    .orElseThrow(() -> new IllegalArgumentException("no.client"));
            List<Request> requests = requestService.getRequestsByClient(client);
            request.setAttribute("requests", requests);
            return "/my-requests.jsp";
        }
        catch (IllegalArgumentException ie){
            request.setAttribute("smthError", ie.getMessage());
            return "/error.jsp";
        }
    }
}
