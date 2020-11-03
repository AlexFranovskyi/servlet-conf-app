package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.service.ReportService;

public class ApproveReportCommand implements Command {
	private final static Logger logger = LogManager.getLogger(ApproveReportCommand.class);
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
		
		if (reportService.approveReport(reportId)) {
			logger.info("The report is approved");
		}
		
		return "redirect:/conferences";
	}

}
