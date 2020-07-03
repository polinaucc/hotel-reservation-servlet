package ua.polina.controller.command.admin;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.utility.PaginationUtility;
import ua.polina.model.entity.Request;
import ua.polina.model.service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetRequestsCommand implements Command {
    private static final String commandName = "get-requests";
    private final RequestService requestService;
    private final PaginationUtility paginationUtility;

    public GetRequestsCommand(RequestService requestService, PaginationUtility paginationUtility) {
        this.requestService = requestService;
        this.paginationUtility = paginationUtility;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = paginationUtility.getCurrentPage(request);
        int recordsPerPage = paginationUtility.getRecordsPerPage(request);
        List<Request> requests = requestService.getAllRequestsPagination(
                paginationUtility.getOffset(currentPage, recordsPerPage), recordsPerPage);
        paginationUtility.paginate(currentPage, recordsPerPage, requestService.numberOfEntries(), requests, commandName, request);
        return "/get-requests.jsp";
    }
}
