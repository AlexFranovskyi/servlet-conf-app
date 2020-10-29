package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dao.UserDao;
import ua.conference.servletapp.model.entity.User;

public class JdbcUserDao implements UserDao {
	
	final static Logger logger = LogManager.getLogger(JdbcUserDao.class);
	
	public static final String SQL_INSERT_NEW_USER = "INSERT INTO usr (username, email, pass, usr_role) VALUES (?, ?, ?, ?)";
	
	
	private Connection connection;
	
	public JdbcUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public User create(User user) {
		ResultSet rs = null;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {

			int k = 1;
			pstmt.setString(k++, user.getUsername());
			pstmt.setString(k++, user.getEmail());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getRole().toString());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getLong(1));
				}
			}
		} catch (SQLException ex) {
			logger.error("Some problems while user insertion", ex);
		} finally {
			closeAutoclosable(rs);
		}

		return user;
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

	@Override
	public Optional<User> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> findByNameOrEmail(String name, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void closeAutoclosable(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception ex) {
				throw new IllegalStateException("Cannot close ResultSet" + ac);
			}
		}	
	}

}
