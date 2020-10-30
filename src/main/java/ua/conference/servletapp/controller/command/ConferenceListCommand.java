package ua.conference.servletapp.controller.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ua.conference.servletapp.model.entity.Conference;

public class ConferenceListCommand implements Command {

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
		
		request.setAttribute("showFutureEvents", "yes");
		request.setAttribute("page", page);
		return "WEB-INF/views/conferences.jsp";
	}

}
