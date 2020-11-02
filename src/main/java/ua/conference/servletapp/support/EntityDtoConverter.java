package ua.conference.servletapp.support;

import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.model.dto.UserDto;
import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.model.entity.Report;
import ua.conference.servletapp.model.entity.User;

public class EntityDtoConverter {
	
	private EntityDtoConverter() {
		
	}
	
	public static UserDto convertUserToDto(User user) {
		return UserDto.builder().id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.role(user.getRole().toString())
				.build();
	}
	
	public static ReportDto convertReportToDto(Report report) {
		return ReportDto.builder()
				.id(report.getId())
				.name(report.getName())
				.speaker(report.getSpeaker())
				.conferenceName(report.getConference())
				.approved(report.isApproved())
				.build();
	}
	
	public static ConferenceDto convertConferenceToDto(Conference conference) {
		return ConferenceDto.builder()
				.id(conference.getId())
				.conferenceName(conference.getConferenceName())
				.localDateTime(conference.getLocalDateTime())
				.location(conference.getLocation())
				.visitorCounter(conference.getVisitorCounter())
				.reportCounter(conference.getReportCounter())
				.arrivedVisitorsAmount(conference.getArrivedVisitorsAmount())
				.build();
	}
	
}
