package ua.polina.controller.command.auth;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.utility.CommandSessionUtility;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        CommandSessionUtility.logoutFromSession(request);
        request.getSession().invalidate();
        return "redirect:index.jsp";
    }
}
