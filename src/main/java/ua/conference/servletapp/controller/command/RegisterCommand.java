package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.RegistrationService;

public class RegisterCommand implements Command {
	private final static Logger logger = LogManager.getLogger(RegisterCommand.class);
	
	private RegistrationService registrationService = new RegistrationService();

	@Override
	public String execute(HttpServletRequest request) {
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String role = request.getParameter("role");

		if( name == null || name.equals("") || pass == null || pass.equals("") ||
				email == null || email.equals("") || role == null || role.equals("")){
			logger.info("Invalid user registration information");
            return "WEB-INF/views/registration.jsp";
        }
		
		if (registrationService.createUser(name, email, pass, role)) {
			return "redirect:/authentication";
		}
		logger.info("Attempt to create account with duplicate email or username entry");
		request.setAttribute("message", "userAlreadyExists");
		return "WEB-INF/views/registration.jsp";
	}

}
