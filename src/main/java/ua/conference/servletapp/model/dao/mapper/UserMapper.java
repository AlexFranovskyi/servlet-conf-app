package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.User;

public class UserMapper implements ObjectMapper<User>{

	@Override
	public User extractFromResultSet(ResultSet rs) throws SQLException {
		return User.builder()
				.id(rs.getLong("id"))
				.username(rs.getString("username"))
				.email(rs.getString("email"))
				.password(rs.getNString("pass"))
				.role(User.Role.valueOf(rs.getString("usr_role")))
				.build();
	}

	@Override
	public User makeUnique(Map<Long, User> cache, User user) {
		cache.putIfAbsent(user.getId(), user);
		return cache.get(user.getId());
	}
	

}
