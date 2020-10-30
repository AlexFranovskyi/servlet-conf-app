package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.User;

public class UserMapper implements ObjectMapper<User> {


    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("pass"));
        user.setRole(User.Role.valueOf(rs.getString("usr_role")));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache,
                              User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
