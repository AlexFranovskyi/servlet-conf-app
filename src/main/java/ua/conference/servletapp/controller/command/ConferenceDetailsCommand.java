package ua.conference.servletapp.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.service.ConferenceService;

public class ConferenceDetailsCommand implements Command {
	private final static Logger logger = LogManager.getLogger(ConferenceDetailsCommand.class);
	
	private ConferenceService conferenceService = new ConferenceService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = Long.parseLong(request.getParameter("conferenceId"));
		
		Optional<ConferenceDto> opt= conferenceService.findConferenceDtoById(conferenceId);
		if (opt.isPresent()) {
			request.setAttribute("conference", opt.get());
			return "WEB-INF/views/conferenceDetails.jsp";
		}
		logger.info("Invalid attempt to receive conference object from DB");
		return "WEB-INF/views/conferenceDetails.jsp";
	}

}
