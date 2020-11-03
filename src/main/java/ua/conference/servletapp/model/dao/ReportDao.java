package ua.conference.servletapp.model.dao;

import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.model.entity.Report;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.Page;

public interface ReportDao extends GenericDao<Report>{
	Page<ReportDto> findAllByConferenceId(long id, int begin, int end, String sort);
	boolean createReportWithNameAndSpeaker(String name, long conferenceId, long speakerId);
	boolean createReportWithName(String name, long conferenceId);
	boolean updateReportWithSpeaker(long reportId, long speakerId);
	boolean removeSpeakerFromReport(long reportId);
	boolean approveReport(long reportId);
	boolean disapproveReport(long reportId);
	Page<ReportDto> findAllBySpeakerId(long speakerId, int begin, int end, String sort);

}
