package ua.conference.servletapp.controller.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ConferenceService;
import ua.conference.servletapp.support.Constants;

public class CreateConferenceCommand implements Command {
	private final static Logger logger = LogManager.getLogger(CreateConferenceCommand.class);
	private ConferenceService conferenceService = new ConferenceService();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		String name = request.getParameter("name");
		String localDateTimeString = request.getParameter("localDateTime");
		String location = request.getParameter("location");
		
		if( name == null || name.equals("") || localDateTimeString == null || localDateTimeString.equals("") ||
				location == null || location.equals("")){
			System.out.println(name + localDateTimeString + location);
			logger.info("Invalid conference registration information");
			request.setAttribute("message", "invalidData");
            return "WEB-INF/views/conferences.jsp";
        }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.LOCAL_TIME_DATE_PATTERN);
		LocalDateTime localDateTime = null;
		
		try {
			localDateTime = LocalDateTime.parse(localDateTimeString, formatter);
		} catch (DateTimeParseException ex) {
			logger.info("Invalid dataTime format in conference input registration information", ex);
			request.setAttribute("message", "invalidData");
            return "WEB-INF/views/conferences.jsp";
		}
		
		if (conferenceService.createConference(name, localDateTime, location)) {
			logger.info("New conference is created");
			return "redirect:/conferences";
		}
		
		logger.info("Something went wrong while conference creation");
		request.setAttribute("message", "failedOperation");
		return "WEB-INF/views/conferences.jsp";
	}

}
