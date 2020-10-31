package ua.conference.servletapp.model.service;

import java.time.LocalDateTime;
import java.util.List;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.entity.Conference;

public class ConferenceService {
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	/*
	public Page<ConferenceDto> findAllConferencesDto(Pageable pageable){
		return conferenceRepository.findAll(pageable).map(EntityDtoConverter::convertConferenceToDto);
	}
	
	public ConferenceDto findConferenceDtoById(long id) {
		Optional<Conference> conference = conferenceRepository.findById(id);
		return EntityDtoConverter.convertConferenceToDto(conference.get());
	}
	 */
	
	public List<ConferenceDto> findConferencesDtoSelectedByTime(String showFutureEvents, Pageable pageable){
		if ("yes".equals(showFutureEvents)) {
			return conferenceRepository.findAllByLocalDateTimeAfter(LocalDateTime.now(), pageable)
					.map(EntityDtoConverter::convertConferenceToDto);
		}
		return conferenceRepository.findAllByLocalDateTimeBefore(LocalDateTime.now(), pageable)
				.map(EntityDtoConverter::convertConferenceToDto);
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
