package ua.conference.servletapp.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.conference.servletapp.model.entity.User;
import ua.conference.servletapp.model.service.UserService;

public class UserListCommand implements Command {
	private UserService userService = new UserService();

	@Override
	public String execute(HttpServletRequest request) {
		List<User> list = userService.findAll();
		
		request.setAttribute("users", list);
		
		return "WEB-INF/views/userList.jsp";
	}

}
