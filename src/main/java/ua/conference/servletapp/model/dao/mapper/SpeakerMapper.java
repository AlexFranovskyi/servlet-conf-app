package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.User;

public class SpeakerMapper implements ObjectMapper<User> {

	@Override
	public User extractFromResultSet(ResultSet rs) throws SQLException {
		return User.builder()
				.id(rs.getLong("speaker_id"))
				.username(rs.getString("speaker_name"))
				.email(rs.getString("speaker_email"))
				.password(rs.getNString("speaker_pass"))
				.role(User.Role.valueOf(rs.getString("speaker_role")))
				.build();
	}

	@Override
	public User makeUnique(Map<Long, User> cache, User speaker) {
		cache.putIfAbsent(speaker.getId(), speaker);
		return cache.get(speaker.getId());
	}

}
