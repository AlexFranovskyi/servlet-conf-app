package ua.conference.servletapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.conference.servletapp.controller.command.*;


public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> commands = new HashMap<>();
	
	@Override
	public void init(ServletConfig servletConfig) {
		servletConfig.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
		
		commands.put("logout",
                new LogoutCommand());
        commands.put("login",
                new LoginCommand());
        commands.put("exception",
        		new ExceptionCommand());
        commands.put("authentication",
        		new AuthenticationCommand());
        commands.put("registration",
        		new RegistrationCommand());
        commands.put("register",
        		new RegisterCommand());
        commands.put("user_list",
        		new UserListCommand());
        commands.put("conferences",
        		new ConferenceListCommand());
        commands.put("post_conference",
        		new CreateConferenceCommand());
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		processRequest(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/servlet-conf-app/" , "");
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);
        
        if(page.contains("redirect:")){
            response.sendRedirect(page.replace("redirect:", "/servlet-conf-app"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
