package ua.polina.controller.command.auth;

import ua.polina.controller.command.MultipleMethodCommand;
import ua.polina.controller.command.utility.CommandBCryptUtility;
import ua.polina.controller.command.utility.CommandSessionUtility;
import ua.polina.model.entity.User;
import ua.polina.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand extends MultipleMethodCommand {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request) {
        return "/login.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isEmpty() || !CommandBCryptUtility.isPasswordMatches(password, user.get().getPassword())) {
            return "redirect:/login?error";
        } else {
            CommandSessionUtility.setUserForSession(request, user.get());
            return "redirect:/";
        }
    }
}
