package ua.polina.controller.command.utility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class PaginationUtility {
    private final static int DEFAULT_PAGINATION_PAGE = 1;
    private final static int DEFAULT_PAGINATION_RECORDS = 2;

    public <T> void paginate(int currentPage, int recordsPerPage, int entries, List<T> entities,
                             String command, HttpServletRequest request) {
        int numberOfPages = (int) Math.ceil((double) entries / recordsPerPage);

        request.setAttribute("entities", entities);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("command", command);
    }

    public int getCurrentPage(HttpServletRequest request) {
        return getPaginationParameter(request.getParameter("currentPage"), DEFAULT_PAGINATION_PAGE);
    }

    public int getRecordsPerPage(HttpServletRequest request) {
        return getPaginationParameter(request.getParameter("recordsPerPage"), DEFAULT_PAGINATION_RECORDS);
    }

    private int getPaginationParameter(String parameter, int defaultParameter) {
        int result;

        if (Objects.isNull(parameter)) {
            return defaultParameter;
        }

        try {
            result = Integer.parseInt(parameter);
        } catch (NumberFormatException e) {
            result = defaultParameter;
        }

        return result > 0 ? result : defaultParameter;
    }

    public int getOffset(int currentPage, int recordsPerPage) {
        return currentPage * recordsPerPage - recordsPerPage;
    }
}
