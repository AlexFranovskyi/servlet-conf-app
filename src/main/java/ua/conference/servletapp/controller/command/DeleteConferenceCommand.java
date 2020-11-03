package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ConferenceService;

public class DeleteConferenceCommand implements Command {
	private final static Logger logger = LogManager.getLogger(DeleteConferenceCommand.class);
	private ConferenceService conferenceService = new ConferenceService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = Long.parseLong(request.getParameter("conferenceId"));
		
		if (conferenceService.deleteConference(conferenceId)) {
			logger.info("Conference is successfully deleted");
			return "redirect:/conferences";
		}
		
		logger.info("Conference deleting is failed");
		request.setAttribute("message", "failedOperation");
		return "WEB-INF/views/conferenceDetails.jsp";
	}

}
