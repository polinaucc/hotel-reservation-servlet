package ua.polina.controller;

import ua.polina.controller.command.Command;
import ua.polina.controller.command.admin.AddDescriptionCommand;
import ua.polina.controller.command.admin.AddRoomCommand;
import ua.polina.controller.command.auth.LoginCommand;
import ua.polina.controller.command.auth.LogoutCommand;
import ua.polina.controller.command.auth.RegisterCommand;
import ua.polina.controller.command.client.AddRequestCommand;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Request;
import ua.polina.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/add-description", "/register", "/login"})
public class AppServlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();

    private DescriptionService descriptionService = new DescriptionService();
    private ClientService clientService = new ClientService();
    private UserService userService = new UserService();
    private RequestService requestService = new RequestService();
    private RoomService roomService = new RoomService();

    public void init(ServletConfig servletConfig) {
        commands.put("add-description", new AddDescriptionCommand(descriptionService));
        commands.put("register", new RegisterCommand(clientService));
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("add-request", new AddRequestCommand(requestService, descriptionService, clientService));
        commands.put("add-room", new AddRoomCommand(descriptionService, roomService));
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

//        if (path.equals("add-room")) {
//            List<Description> descriptions = descriptionService.getAllDescriptions();
//            request.setAttribute("descriptions", descriptions);
//        }

        if (page.contains("redirect")) {
            response.sendRedirect(page.replaceAll("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
