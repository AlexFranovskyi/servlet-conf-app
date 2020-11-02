package ua.conference.servletapp.model.service;

import java.time.LocalDateTime;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.Page;

public class ConferenceService {
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	/*
	public Page<ConferenceDtoMapper> findAllConferencesDto(Pageable pageable){
		return conferenceRepository.findAll(pageable).map(EntityDtoConverter::convertConferenceToDto);
	}
	
	public ConferenceDtoMapper findConferenceDtoById(long id) {
		Optional<Conference> conference = conferenceRepository.findById(id);
		return EntityDtoConverter.convertConferenceToDto(conference.get());
	}
	 */
	
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
	/*
	@Transactional
	public Conference updateConference(long id, LocalDateTime localDateTime,
			String location, int arrivedVisitorsAmount
			) {
		Conference newConference = conferenceRepository.findById(id).get();
		newConference.setLocalDateTime(localDateTime);
		newConference.setLocation(location);
		newConference.setArrivedVisitorsAmount(arrivedVisitorsAmount);
		return conferenceRepository.save(newConference);
	}
	
	@Transactional
	public Conference addVisitor(long conferenceId, User user) {
		Conference conference = conferenceRepository.findById(conferenceId).get();
		conference.getVisitors().add(user);
		return conferenceRepository.save(conference);
	}
	
	public void deleteConference(long id) {
		conferenceRepository.deleteById(id);
	}
	*/

}
