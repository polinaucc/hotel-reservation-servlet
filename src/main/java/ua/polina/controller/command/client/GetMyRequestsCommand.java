package ua.polina.controller.command.client;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.controller.command.Command;
import ua.polina.controller.command.utility.CommandSessionUtility;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.User;
import ua.polina.model.service.ClientService;
import ua.polina.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetMyRequestsCommand implements Command {
    private final RequestService requestService;
    private final ClientService clientService;
    private final Logger LOGGER = LogManager.getLogger(GetMyRequestsCommand.class);
    private ResourceBundle rb;

    public GetMyRequestsCommand(RequestService requestService, ClientService clientService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "US"));

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
        } catch (IllegalArgumentException ie) {
            LOGGER.warn(rb.getString(ie.getMessage()));
            request.setAttribute("smthError", ie.getMessage());
            return "/error.jsp";
        }
    }
}
