package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.service.ConferenceService;
import ua.conference.servletapp.support.Page;

public class ConferenceListCommand implements Command {
	private final static Logger logger = LogManager.getLogger(ConferenceListCommand.class);
	
	private ConferenceService conferenceService = new ConferenceService();

	@Override
	public String execute(HttpServletRequest request) {
		
		String sort = request.getParameter("sort");
		if (sort == null) {
			sort = "local_date_time DESC";
		}
		
		String showFutureEvents = request.getParameter("showFutureEvents");
		if (showFutureEvents == null) {
			showFutureEvents = "yes";
		}
		
		String pageNumberString = request.getParameter("pageNumber");
		int pageNumber = 0;
		
		if (pageNumberString != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));							
			} catch (NumberFormatException ex) {
				logger.info("Invalid number of page passed value", ex);
			}
		}
		
		Page<ConferenceDto> page = conferenceService
				.findConferencesDtoSelectedByTime(showFutureEvents, pageNumber, sort);
		
		request.setAttribute("showFutureEvents", showFutureEvents);
		request.setAttribute("page", page);
		
		return "WEB-INF/views/conferences.jsp";
	}

}
