package ua.polina.controller;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.admin.AddDescriptionCommand;
import ua.polina.controller.command.auth.LoginCommand;
import ua.polina.controller.command.auth.LogoutCommand;
import ua.polina.controller.command.auth.RegisterCommand;
import ua.polina.model.service.ClientService;
import ua.polina.model.service.DescriptionService;
import ua.polina.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/add-description", "/register", "/login"})
public class AppServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    private DescriptionService descriptionService = new DescriptionService();
    private ClientService clientService = new ClientService();
    private UserService userService = new UserService();

    public void init(ServletConfig servletConfig){
        commands.put("add-description", new AddDescriptionCommand(descriptionService));
        commands.put("register", new RegisterCommand(clientService));
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getRequestURI();
        path = path.replace("/", "");
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(request);

        if(page.contains("redirect")){
            response.sendRedirect(page.replaceAll("redirect:", ""));
        }
        else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
