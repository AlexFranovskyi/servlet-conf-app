package ua.conference.servletapp.controller.command;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.conference.servletapp.model.entity.User;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		HashSet<String> loggedUsers = (HashSet<String>) session.getServletContext()
				.getAttribute("loggedUsers");
		loggedUsers.remove(username);
		session.getServletContext().setAttribute("loggedUsers", loggedUsers);
		
		CommandUtility.setUserNameAndRole(request, User.Role.GUEST, "Guest");
		return "redirect:/";
	}

}
