package ua.conference.servletapp.model.service;

import java.time.LocalDateTime;
import java.util.Optional;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.EntityDtoConverter;
import ua.conference.servletapp.support.Page;

public class ConferenceService {
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
		
	public Optional<ConferenceDto> findConferenceDtoById(long id) {
		ConferenceDao dao = daoFactory.createConferenceDao();
		Optional<Conference> conferenceOpt = dao.findById(id);
		dao.close();
		return conferenceOpt.map(EntityDtoConverter::convertConferenceToDto);
	}
	 
	public Page<ConferenceDto> findConferencesDtoSelectedByTime(String showFutureEvents, int pageNumber, String sort){
		int begin = pageNumber * Constants.PAGE_SIZE;
		int end = (pageNumber + 1) * Constants.PAGE_SIZE;
		
		ConferenceDao dao = daoFactory.createConferenceDao();
		
		boolean future = true;
		if ("no".contentEquals(showFutureEvents)) {
			future = false;
		}
		Page<ConferenceDto> page = dao.findAllByLocalDateTimeSorted(begin, end, future, sort);
			
		dao.close();
		return page;
	}
	
	public boolean createConference(String name, LocalDateTime localDateTime, String location) {
		Conference newConference = Conference
				.builder()
				.conferenceName(name)
				.localDateTime(localDateTime)
				.location(location)
				.build();
		
		ConferenceDao dao = daoFactory.createConferenceDao();
		boolean res = true;
		
		dao.create(newConference);
		if (newConference.getId() == 0) {
			res = false;
		}
		dao.close();
		return res;
	}
	
	public boolean createConferenceAndVisit(String name, LocalDateTime localDateTime, String location, long adminId) {
		Conference newConference = Conference
				.builder()
				.conferenceName(name)
				.localDateTime(localDateTime)
				.location(location)
				.build();
		
		ConferenceDao dao = daoFactory.createConferenceDao();
		boolean res = true;
		
		res = dao.createAndVisit(newConference, adminId);
		dao.close();
		return res;
	}

	public boolean updateConference(long id, LocalDateTime localDateTime,
			String location, int arrivedVisitorsAmount
			) {
				
		ConferenceDao dao = daoFactory.createConferenceDao();
		boolean res = dao.updateConference(id, localDateTime, location, arrivedVisitorsAmount);
		dao.close();
		
		return res;
	}
	
	public boolean addVisitor(long conferenceId, long userId) {
		ConferenceDao dao = daoFactory.createConferenceDao();
		boolean result = dao.addVisitor(conferenceId, userId);
		dao.close();
		return result;
	}
	
	
	public boolean deleteConference(long id) {
		boolean result = false;	
		ConferenceDao dao = daoFactory.createConferenceDao();
		
		if (dao.delete(id)) {
			result = true;
		}
		dao.close();
		return result;
	}
	
}
