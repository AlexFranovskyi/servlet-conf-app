package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.entity.User.Role;
import ua.conference.servletapp.model.service.ReportService;

public class CreateReportCommand implements Command {
	private final static Logger logger = LogManager.getLogger(CreateReportCommand.class);
	private ReportService reportService = new ReportService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = 0;
			String name = "";
		try {
			conferenceId = Long.parseLong(request.getParameter("conferenceId"));
			name = request.getParameter("name");
		} catch (Exception ex) {
			logger.error("Could not recieve parameters", ex);
			return "redirect:/";
		}
		
		if( name.equals("") || conferenceId <= 0){
			logger.info("Invalid report post information");
			request.setAttribute("message", "invalidData");
            return "WEB-INF/views/conferences.jsp";
        }
		
		Role role = (Role) request.getSession().getAttribute("role");
		System.out.println(role);
		boolean result = true;
		
		if (role == Role.SPEAKER) {
			long speakerId = (long)request.getSession().getAttribute("userId");
			result = reportService.createReportWithNameAndSpeaker(name, conferenceId, speakerId);
		} else {
			result = reportService.createReportWithName(name, conferenceId);
		}
		if (result) {
			logger.info("Report is successfully posted");
			return "redirect:/conferences";
		}
		logger.info("Report creation is failed");
		return "redirect:/conferences";
	}

}
