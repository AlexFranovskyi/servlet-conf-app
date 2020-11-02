package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

public class VisitConferenceCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		//long conferenceId = Long.getLong(request.getParameter("conferenceId"));
		String conferenceId = request.getParameter("conferenceId");
		
		Long userId = (long)request.getSession().getAttribute("userId");
		
		System.out.println(conferenceId + "aa" + userId);

		return "redirect:/conferences";
	}

}
