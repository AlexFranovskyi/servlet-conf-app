package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.entity.User;
import ua.conference.servletapp.model.service.RegistrationService;

public class RegisterCommand implements Command {
	final static Logger logger = LogManager.getLogger(RegisterCommand.class);
	
	RegistrationService registrationService = new RegistrationService();

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
		
		User newUser = User.builder().username(name)
				.email(email)
				.password(pass)
				.role(User.Role.valueOf(role))
				.build();
		if (registrationService.createUser(newUser)) {
			logger.info("Attempt to create account with duplicate email or username entry");
			return "redirect:/authentication";
		}
		request.setAttribute("userExists", true);
		return "WEB-INF/views/registration.jsp";
	}

}
