package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ConferenceService;

public class VisitConferenceCommand implements Command {
	private final static Logger logger = LogManager.getLogger(VisitConferenceCommand.class);
	
	private ConferenceService conferenceService = new ConferenceService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = 0;
		try {
			conferenceId = Long.parseLong(request.getParameter("conferenceId"));
		} catch (Exception ex) {
			logger.error("Invalid parsing of recieved parameter", ex);
			return "redirect:/";
		}
		
		long userId = (long)request.getSession().getAttribute("userId");
		
		if (conferenceService.addVisitor(conferenceId, userId)) {
			logger.info("visitor is succesfully added to conference");
		} 
		return "redirect:/conferences";
	}

}
