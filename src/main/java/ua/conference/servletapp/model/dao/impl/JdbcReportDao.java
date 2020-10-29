package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import ua.conference.servletapp.model.dao.ReportDao;
import ua.conference.servletapp.model.entity.Report;

public class JdbcReportDao implements ReportDao {
private Connection connection;
	
	public JdbcReportDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Report create(Report entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Report> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Report entity) {
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
