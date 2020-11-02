package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.dto.ConferenceDto;

public class ConferenceDtoMapper implements ObjectMapper<ConferenceDto> {

	@Override
	public ConferenceDto extractFromResultSet(ResultSet rs) throws SQLException {
		return ConferenceDto.builder()
				.id(rs.getLong("conf_id"))
				.conferenceName(rs.getString("conference_name"))
				.localDateTime(rs.getTimestamp("local_date_time").toLocalDateTime())
				.location(rs.getString("location"))
				.reportCounter(rs.getInt("reportCounter"))
				.visitorCounter(rs.getInt("visitorCounter"))
				.arrivedVisitorsAmount(rs.getInt("arrived_visitors_amount"))
				.build();
		
	}

	@Override
	public ConferenceDto makeUnique(Map<Long, ConferenceDto> cache, ConferenceDto confDto) {
		cache.putIfAbsent(confDto.getId(), confDto);
		return cache.get(confDto.getId());
	}

}
