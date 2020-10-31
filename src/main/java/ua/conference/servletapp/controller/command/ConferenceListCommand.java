package ua.conference.servletapp.controller.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.entity.Conference;

public class ConferenceListCommand implements Command {
	private final static Logger logger = LogManager.getLogger(ConferenceListCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		List<Conference> page= new ArrayList<>();
		Conference conference = Conference.builder().id(10)
				.conferenceName("lala")
				.localDateTime(LocalDateTime.now())
				.arrivedVisitorsAmount(10)
				.location("New York")
				.reportCounter(10)
				.visitorCounter(10)
				.build();
		
		Conference conference2 = Conference.builder().id(20)
				.conferenceName("lalkaa")
				.localDateTime(LocalDateTime.now())
				.arrivedVisitorsAmount(20)
				.location("Mexico")
				.reportCounter(100)
				.visitorCounter(100)
				.build();
		page.add(conference);
		page.add(conference2);
		
		String sort = request.getParameter("sort");
		if (sort == null) {
			sort = "localDateTime,DESC";
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
		
		request.setAttribute("sort", sort);
		request.setAttribute("showFutureEvents", showFutureEvents);
		request.setAttribute("page", page);
		request.setAttribute("totalPages", 20);
		request.setAttribute("pageNumber", pageNumber);
		
		
		return "WEB-INF/views/conferences.jsp";
	}

}
