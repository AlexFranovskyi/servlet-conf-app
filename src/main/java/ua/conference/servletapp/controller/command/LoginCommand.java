package ua.conference.servletapp.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.entity.User;
import ua.conference.servletapp.model.service.RegistrationService;

public class LoginCommand implements Command {
	private final static Logger logger = LogManager.getLogger(LoginCommand.class);
	private RegistrationService registrationService = new RegistrationService();

	@Override
	public String execute(HttpServletRequest request) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if( username == null || username.equals("") || password == null || password.equals("")  ){            
            request.setAttribute("message", "invalidData");
            return "WEB-INF/views/login.jsp";
        }
		
        Optional<User> opt = registrationService.findUserByUsername(username);
        
        if (!opt.isPresent()) {
        	request.setAttribute("message", "userNotExists");
            return "WEB-INF/views/login.jsp";
        } 
        User user = opt.get();
        
        if(!user.getPassword().equals(password)) {
        	request.setAttribute("message", "invalidPassword");
            return "WEB-INF/views/login.jsp";
        }

        if(CommandUtility.checkUserIsLogged(request, username)){
        	logger.info("attempt to login under already logined account");
            return "/error.jsp";
        }
        request.getSession().setAttribute("userId", user.getId());
        CommandUtility.setUserNameAndRole(request, user.getRole(), username);
        logger.info("Successful logining");
        return "redirect:/";

	}
}
