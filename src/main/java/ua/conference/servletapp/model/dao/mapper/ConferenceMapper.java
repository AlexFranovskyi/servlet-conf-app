package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.Conference;

public class ConferenceMapper implements ObjectMapper<Conference> {

	@Override
	public Conference extractFromResultSet(ResultSet rs) throws SQLException {
		return Conference.builder()
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
	public Conference makeUnique(Map<Long, Conference> cache, Conference conference) {
		cache.putIfAbsent(conference.getId(), conference);
		return cache.get(conference.getId());
	}

}
