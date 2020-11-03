package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ReportService;

public class BecomeSpeakerCommand implements Command {
	private final static Logger logger = LogManager.getLogger(BecomeSpeakerCommand.class);
	private ReportService reportService = new ReportService();
	
	@Override
	public String execute(HttpServletRequest request) {
		long reportId = 0;
		try {
			reportId = Long.parseLong(request.getParameter("reportId"));
		} catch (Exception ex) {
			logger.error("Invalid parsing of recieved parameter", ex);
			return "redirect:/";
		}
		long speakerId = (long)request.getSession().getAttribute("userId");
		
		if (reportService.updateReportWithSpeaker(reportId, speakerId)) {
			logger.info("Speaker is succesfully added to report");
		}
		
		return "redirect:/conferences";
	}

}
