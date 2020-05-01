package ua.polina.controller.command.utility;

import ua.polina.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandSessionUtility {
    public static void setUserForSession(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        session.setAttribute("principals", user);
    }

    public static void logoutFromSession(HttpServletRequest request){
        request.getSession().removeAttribute("principals");
    }
}
