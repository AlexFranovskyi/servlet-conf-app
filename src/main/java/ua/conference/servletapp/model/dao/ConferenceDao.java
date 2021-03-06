package ua.conference.servletapp.model.dao;

import java.time.LocalDateTime;

import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.support.Page;

public interface ConferenceDao extends GenericDao<Conference>{
	Page<ConferenceDto> findAllByLocalDateTimeSorted(int begin, int end, boolean future, String sort);
	boolean addVisitor(long conferenceId, long userId);
	
	boolean createAndVisit(Conference conference, long userId);
	boolean updateConference(long id, LocalDateTime localDateTime, String location, int arrivedVisitorsAmount);

}
