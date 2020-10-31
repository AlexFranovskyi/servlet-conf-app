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

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.entity.Conference;

public class JdbcConferenceDao implements ConferenceDao {
	private Connection connection;
	
	private final static Logger logger = LogManager.getLogger(JdbcConferenceDao.class);
	
	public static final String SQL_INSERT_NEW_CONFERENCE = "INSERT INTO conference (conference_name, local_date_time, location) VALUES (?, ?, ?)";

	public JdbcConferenceDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Conference create(Conference conference) {
		ResultSet rs = null;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_NEW_CONFERENCE, Statement.RETURN_GENERATED_KEYS)) {

			int k = 1;
			pstmt.setString(k++, conference.getConferenceName());
			pstmt.setString(k++, conference.getLocalDateTime().toString());
			pstmt.setString(k++, conference.getLocation());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					conference.setId(rs.getLong(1));
				}
			}
		} catch (SQLException ex) {
			logger.error("Some problems while conference insertion", ex);
		} finally {
			closeAutoclosable(rs);
		}
		return conference;
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
