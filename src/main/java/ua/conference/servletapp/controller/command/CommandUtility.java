package ua.conference.servletapp.controller.command;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.conference.servletapp.model.entity.User;

public class CommandUtility {
	static void setUserNameAndRole(HttpServletRequest request, User.Role role, String username) {
		HttpSession session = request.getSession();

		session.setAttribute("username", username);
		session.setAttribute("role", role);

	}

	static boolean checkUserIsLogged(HttpServletRequest request, String username) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");

		if (loggedUsers.stream().noneMatch(username::equals)) {
			synchronized (CommandUtility.class) {
				if (loggedUsers.stream().noneMatch(username::equals)) {
					loggedUsers.add(username);
					request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
					return false;
				}
			}
		}
		return true;
	}

}
