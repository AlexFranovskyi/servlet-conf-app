package ua.conference.servletapp.controller.command;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.conference.servletapp.model.entity.User;

public class CommandUtility {
	static void setUserNameAndRole(HttpServletRequest request, User.Role role, String userName) {
		HttpSession session = request.getSession();

		session.setAttribute("userName", userName);
		session.setAttribute("role", role);

	}

	static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");

		if (loggedUsers.stream().anyMatch(userName::equals)) {
			return true;
		}
		loggedUsers.add(userName);
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
		return false;
	}

}
