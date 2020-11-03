package ua.conference.servletapp.model.service;

import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dao.ReportDao;
import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.Page;

public class ReportService {
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
		
	public Page<ReportDto> findAllReportsDtoByConferenceId(long id, int pageNumber){
		int begin = pageNumber * Constants.PAGE_SIZE;
		int end = (pageNumber + 1) * Constants.PAGE_SIZE;
		
		ReportDao dao = daoFactory.createReportDao();
		Page<ReportDto> page = dao.findAllByConferenceId(id, begin, end, Constants.REPORT_SORTING);
		
		return page;
	}
	
	public boolean createReportWithName(String name, long conferenceId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result = dao.createReportWithName(name, conferenceId);
		dao.close();
		return result;
	}
	
	public boolean createReportWithNameAndSpeaker(String name, long conferenceId, long speakerId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result = dao.createReportWithNameAndSpeaker(name, conferenceId, speakerId);
		dao.close();
		return result;
	}
	
	
	public boolean updateReportWithSpeaker(long reportId, long speakerId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result = dao.updateReportWithSpeaker(reportId, speakerId);
		dao.close();
		return result;
	}
	
	public boolean approveReport(long reportId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result =  dao.approveReport(reportId);
		dao.close();
		return result;
	}
	
	
	public boolean disapproveReport(long reportId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result =  dao.disapproveReport(reportId);
		dao.close();
		return result;
	}
	
	public boolean clearSpeakerFromReport(long reportId) {
		ReportDao dao = daoFactory.createReportDao();
		boolean result = dao.removeSpeakerFromReport(reportId);
		dao.close();
		return result;	
	}
	
	public Page<ReportDto> findAllReportsBySpeaker(long speakerId, int pageNumber){
		int begin = pageNumber * Constants.PAGE_SIZE;
		int end = (pageNumber + 1) * Constants.PAGE_SIZE;
		
		ReportDao dao = daoFactory.createReportDao();
		Page<ReportDto> page = dao.findAllBySpeakerId(speakerId, begin, end, Constants.REPORT_SORTING);
		
		return page;
	}
	
}
