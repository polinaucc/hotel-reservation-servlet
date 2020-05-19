package ua.polina.controller.command;

import javax.servlet.http.HttpServletRequest;

public abstract class MultipleMethodCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String type = request.getMethod();

        return "GET".equals(type) ? executeGet(request) : executePost(request);
    }

    protected abstract String executeGet(HttpServletRequest request);

    protected abstract String executePost(HttpServletRequest request) throws Exception;
}
