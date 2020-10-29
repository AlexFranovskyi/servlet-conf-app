package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dao.ReportDao;
import ua.conference.servletapp.model.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }
    @Override
    public ConferenceDao createConferenceDao() {
        return new JdbcConferenceDao(getConnection());
    }
    @Override
    public ReportDao createReportDao() {
    	return new JdbcReportDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
