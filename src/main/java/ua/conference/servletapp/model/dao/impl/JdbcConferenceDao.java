package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.entity.Conference;

public class JdbcConferenceDao implements ConferenceDao {
	private Connection connection;

	public JdbcConferenceDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Conference create(Conference entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Conference> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Conference> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Conference entity) {
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

}
