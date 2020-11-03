package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.model.service.ReportService;
import ua.conference.servletapp.support.Page;

public class ReportListCommand implements Command {
	private final static Logger logger = LogManager.getLogger(ReportListCommand.class);
	private ReportService reportService = new ReportService();

	@Override
	public String execute(HttpServletRequest request) {
		long conferenceId = 0;
		try {
			conferenceId = Long.parseLong(request.getParameter("conferenceId"));
		} catch (Exception ex) {
			logger.error("Invalid parsing of recieved parameter", ex);
			return "redirect:/";
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

		request.setAttribute("conferenceId", conferenceId);
		Page<ReportDto> page = reportService
				.findAllReportsDtoByConferenceId(conferenceId, pageNumber);
		
		request.setAttribute("page", page);

		return "WEB-INF/views/reports.jsp";
	}

}
