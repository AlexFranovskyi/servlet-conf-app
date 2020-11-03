package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.model.service.ReportService;
import ua.conference.servletapp.support.Page;

public class SpeakerReportsCommand implements Command {
	private final static Logger logger = LogManager.getLogger(SpeakerReportsCommand.class);
	private ReportService reportService = new ReportService();

	@Override
	public String execute(HttpServletRequest request) {
		
		String pageNumberString = request.getParameter("pageNumber"); 
		int pageNumber = 0;

		if (pageNumberString != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException ex) {
				logger.info("Invalid number of page passed value", ex);
			}
		}
		
		long speakerId = (long)request.getSession().getAttribute("userId");

		Page<ReportDto> page = reportService
				.findAllReportsBySpeaker(speakerId, pageNumber);
		
		request.setAttribute("page", page);
		
		return "WEB-INF/views/speakerReports.jsp";
	}

}
