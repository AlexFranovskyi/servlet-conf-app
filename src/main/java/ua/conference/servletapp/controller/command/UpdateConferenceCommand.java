package ua.conference.servletapp.controller.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ConferenceService;
import ua.conference.servletapp.support.Constants;

public class UpdateConferenceCommand implements Command {
	private final static Logger logger = LogManager.getLogger(UpdateConferenceCommand.class);
	private ConferenceService conferenceService = new ConferenceService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = Long.parseLong(request.getParameter("conferenceId"));
		
		String location = request.getParameter("location");
		String localDateTimeString = request.getParameter("localDateTime");
		int arrivedVisitorsAmount = Integer.parseInt(request.getParameter("arrivedVisitorsAmount"));
		
		if( arrivedVisitorsAmount < 0 || localDateTimeString == null || localDateTimeString.equals("") ||
				location == null || location.equals("")){
			logger.info("Invalid conference registration information");
			request.setAttribute("message", "invalidData");
            return "WEB-INF/views/conferenceDetails.jsp";
        }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_TIME_DATE_PATTERN);
		LocalDateTime localDateTime = null;
		
		try {
			localDateTime = LocalDateTime.parse(localDateTimeString, formatter);
		} catch (DateTimeParseException ex) {
			logger.info("Invalid dataTime format in conference input registration information", ex);
			request.setAttribute("message", "invalidData");
            return "WEB-INF/views/conferenceDetails.jsp";
		}
		
		if (conferenceService.updateConference(conferenceId, localDateTime, location, arrivedVisitorsAmount)) {
			logger.info("Conference is successfully updated");
			return "redirect:/conferences";
		}
			logger.info("Conference updating is failed");
			request.setAttribute("message", "failedOperation");
		return "WEB-INF/views/conferenceDetails.jsp";
	}

}
