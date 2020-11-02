package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.User;

public class VisitorMapper implements ObjectMapper<User> {

	@Override
	public User extractFromResultSet(ResultSet rs) throws SQLException {
		return User.builder()
				.id(rs.getLong("visitor_id"))
				.username(rs.getString("visitor_name"))
				.email(rs.getString("visitor_email"))
				.password(rs.getNString("visitor_pass"))
				.role(User.Role.valueOf(rs.getString("visitor_role")))
				.build();
	}

	@Override
	public User makeUnique(Map<Long, User> cache, User visitor) {
		cache.putIfAbsent(visitor.getId(), visitor);
		return cache.get(visitor.getId());
	}

}
