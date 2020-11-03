package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dao.ReportDao;
import ua.conference.servletapp.model.dao.mapper.ReportDtoMapper;
import ua.conference.servletapp.model.dto.ReportDto;
import ua.conference.servletapp.model.entity.Report;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.Page;

public class JdbcReportDao implements ReportDao {

	private final static Logger logger = LogManager.getLogger(JdbcReportDao.class);

	public static final String SQL_FIND_REPORTS_PAGINATED_BEGIN = "Select (select count(distinct id) from report where conference_id = ?) as total_rows, r.id as report_id, r.report_name, r.is_approved, s.username as speaker_name, c.conference_name from report as r left join usr as s on r.speaker_id = s.id left join conference as c on r.conference_id = c.id where c.id = ? order by ";
	public static final String SQL_FIND_SPEAKER_REPORTS_PAGINATED_BEGIN = "Select (select count(distinct id) from report where speaker_id = ?) as total_rows, r.id as report_id, r.report_name, r.is_approved, s.username as speaker_name, c.conference_name from report as r left join usr as s on r.speaker_id = s.id left join conference as c on r.conference_id = c.id where r.speaker_id = ? order by ";
	public static final String SQL_FIND_REPORTS_PAGINATED_END = " limit ? offset ?";
	
	public static final String SQL_CREATE_REPORT_WITH_NAME = "insert into report (report_name, conference_id) values (?, ?)";
	public static final String SQL_CREATE_REPORT_WITH_NAME_AND_SPEAKER = "insert into report (report_name, conference_id, speaker_id) values (?, ?, ?)";
	public static final String SQL_ADD_SPEAKER_TO_REPORT = "UPDATE report set speaker_id = ? where id = ?";
	public static final String SQL_REMOVE_SPEAKER_FROM_REPORT = "UPDATE report set speaker_id = null where id = ?";
	public static final String SQL_APPROVE_REPORT = "UPDATE report set is_approved = 1 where id = ?";
	public static final String SQL_DISAPPROVE_REPORT = "UPDATE report set is_approved = 0 where id = ?";
	
	private Connection connection;

	public JdbcReportDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Page<ReportDto> findAllByConferenceId(long id, int begin, int end, String sort) {
		List<ReportDto> list = new ArrayList<>();
		ResultSet rs = null;
		int totalRows = 0;

		try (PreparedStatement pstmt = connection
				.prepareStatement(SQL_FIND_REPORTS_PAGINATED_BEGIN + sort + SQL_FIND_REPORTS_PAGINATED_END)) {

			int k = 1;
			pstmt.setLong(k++, id);
			pstmt.setLong(k++, id);
			pstmt.setInt(k++, end);
			pstmt.setInt(k++, begin);

			rs = pstmt.executeQuery();

			ReportDtoMapper reportDtoMapper = new ReportDtoMapper();

			ReportDto reportDto = null;

			while (rs.next()) {
				totalRows = rs.getInt("total_rows");
				reportDto = reportDtoMapper.extractFromResultSet(rs);
				list.add(reportDto);
			}

		} catch (SQLException e) {
			logger.error("Error while extracting report records from db", e);
		} finally {
			closeAutoclosable(rs);
		}

		return new Page<ReportDto>(list, sort, begin, totalRows, Constants.PAGE_SIZE);
	}
	
	

	@Override
	public Page<ReportDto> findAllBySpeakerId(long speakerId, int begin, int end, String sort) {
		List<ReportDto> list = new ArrayList<>();
		ResultSet rs = null;
		int totalRows = 0;

		try (PreparedStatement pstmt = connection
				.prepareStatement(SQL_FIND_SPEAKER_REPORTS_PAGINATED_BEGIN + sort + SQL_FIND_REPORTS_PAGINATED_END)) {

			int k = 1;
			pstmt.setLong(k++, speakerId);
			pstmt.setLong(k++, speakerId);
			pstmt.setInt(k++, end);
			pstmt.setInt(k++, begin);

			rs = pstmt.executeQuery();

			ReportDtoMapper reportDtoMapper = new ReportDtoMapper();

			ReportDto reportDto = null;

			while (rs.next()) {
				totalRows = rs.getInt("total_rows");
				reportDto = reportDtoMapper.extractFromResultSet(rs);
				list.add(reportDto);
			}

		} catch (SQLException e) {
			logger.error("Error while extracting report records from db", e);
		} finally {
			closeAutoclosable(rs);
		}
		return new Page<ReportDto>(list, sort, begin, totalRows, Constants.PAGE_SIZE);
	}

	@Override
	public boolean createReportWithName(String name, long conferenceId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_CREATE_REPORT_WITH_NAME)) {

			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setLong(k++, conferenceId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Some problems while report creating", ex);
		}

		return result;
	}
	
	
	
	@Override
	public boolean approveReport(long reportId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_APPROVE_REPORT)) {

			pstmt.setLong(1, reportId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Can't approve report", ex);
		}	
		return result;
	}
	
	@Override
	public boolean disapproveReport(long reportId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_DISAPPROVE_REPORT)) {

			pstmt.setLong(1, reportId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Can't disapprove report", ex);
		}	
		return result;
	}

	@Override
	public boolean updateReportWithSpeaker(long reportId, long speakerId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_ADD_SPEAKER_TO_REPORT)) {

			int k = 1;
			pstmt.setLong(k++, speakerId);
			pstmt.setLong(k++, reportId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Can't add speaker", ex);
		}	
		return result;
	}

	@Override
	public boolean createReportWithNameAndSpeaker(String name, long conferenceId, long speakerId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_CREATE_REPORT_WITH_NAME_AND_SPEAKER)) {

			int k = 1;
			pstmt.setString(k++, name);
			pstmt.setLong(k++, conferenceId);
			pstmt.setLong(k++, speakerId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Some problems while report creating", ex);
		}

		return result;
	}
	
	

	@Override
	public boolean removeSpeakerFromReport(long reportId) {
		boolean result = false;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_REMOVE_SPEAKER_FROM_REPORT)) {

			pstmt.setLong(1, reportId);

			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException ex) {
			logger.error("Can't remove speaker", ex);
		}	
		return result;
	}

	@Override
	public Report create(Report entity) {
		return null;
	}

	@Override
	public Optional<Report> findById(long id) {
		return null;
	}

	@Override
	public List<Report> findAll() {
		return null;
	}

	@Override
	public void update(Report entity) {
	}

	@Override
	public boolean delete(long id) {
		return false;
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
