package ua.conference.servletapp.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.dao.ConferenceDao;
import ua.conference.servletapp.model.dao.mapper.ConferenceDtoMapper;
import ua.conference.servletapp.model.dao.mapper.ConferenceMapper;
import ua.conference.servletapp.model.dao.mapper.ReportMapper;
import ua.conference.servletapp.model.dao.mapper.SpeakerMapper;
import ua.conference.servletapp.model.dao.mapper.VisitorMapper;
import ua.conference.servletapp.model.dto.ConferenceDto;
import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.model.entity.Report;
import ua.conference.servletapp.model.entity.User;
import ua.conference.servletapp.support.Constants;
import ua.conference.servletapp.support.Page;

public class JdbcConferenceDao implements ConferenceDao {
	private Connection connection;

	private final static Logger logger = LogManager.getLogger(JdbcConferenceDao.class);

	public static final String SQL_INSERT_NEW_CONFERENCE = "INSERT INTO conference (conference_name, local_date_time, location) VALUES (?, ?, ?)";

	public static final String SQL_FIND_ALL = "SELECT c.id as conf_id, c.local_date_time, c.location, "
			+ "c.conference_name, c.arrived_visitors_amount, (select count(distinct r_two.id) from report as r_two "
			+ "where c.id = r_two.conference_id) as reportCounter, r.id as report_id, r.report_name, "
			+ "r.is_approved, r.speaker_id, s.username as speaker_name, s.email as speaker_email, s.pass as speaker_pass, "
			+ "s.usr_role as speaker_role, (select count(distinct chv_two.user_id) from conference_has_visitor as chv_two "
			+ "where c.id = chv_two.conference_id) as visitorCounter, usr.id as visitor_id, usr.username as visitor_name, "
			+ "usr.email as visitor_email, usr.pass as visitor_pass, usr.usr_role as visitor_role FROM conference as c "
			+ "left join report as r on c.id = r.conference_id left join usr as s on r.speaker_id = s.id "
			+ "left join conference_has_visitor as chv on c.id = chv.conference_id left join usr on chv.user_id = usr.id";
	
	public static final String SQL_FIND_FUTURE_DISTINCT_CONFERENCES_SORTED_PAGINATED = 
			"SELECT (select count(distinct id) from conference where local_date_time > now()) as total_pages, "
			+ "c.id as conf_id, c.local_date_time, c.location, c.conference_name, c.arrived_visitors_amount, "
			+ "(select count(distinct r.id) from report as r where c.id = r.conference_id) as reportCounter, "
			+ "(select count(distinct chv.user_id) from conference_has_visitor as chv where c.id = chv.conference_id) as visitorCounter "
			+ "FROM conference as c  where c.local_date_time > now() order by ? limit ? offset ?";
	
	public static final String SQL_FIND_PAST_DISTINCT_CONFERENCES_SORTED_PAGINATED = 
			"SELECT (select count(distinct id) from conference where local_date_time < now()) as total_pages, "
			+ "c.id as conf_id, c.local_date_time, c.location, c.conference_name, c.arrived_visitors_amount, "
			+ "(select count(distinct r.id) from report as r where c.id = r.conference_id) as reportCounter, "
			+ "(select count(distinct chv.user_id) from conference_has_visitor as chv where c.id = chv.conference_id) as visitorCounter "
			+ "FROM conference as c  where c.local_date_time < now() order by ? limit ? offset ?";

	public JdbcConferenceDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Page<ConferenceDto> findAllByLocalDateTimeSorted(int begin, int end, boolean future, String sort) {
		List <ConferenceDto> list = new ArrayList<>();
		

		ResultSet rs = null;
		int totalRows = 0;
		
		String query = "";
		if (future) {
			query = SQL_FIND_FUTURE_DISTINCT_CONFERENCES_SORTED_PAGINATED;
		} else {
			query = SQL_FIND_PAST_DISTINCT_CONFERENCES_SORTED_PAGINATED;
		}

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			
			int k = 1;
			
			pstmt.setString(k++, sort);
			pstmt.setInt(k++, end);
			pstmt.setInt(k++, begin);

			rs = pstmt.executeQuery();

			ConferenceDtoMapper conferenceDtoMapper = new ConferenceDtoMapper();
			
			ConferenceDto conferenceDto = null;
			
			while (rs.next()) {
				totalRows = rs.getInt("total_pages");			
				conferenceDto = conferenceDtoMapper.extractFromResultSet(rs);
				list.add(conferenceDto);
			}

		} catch (SQLException e) {
			logger.error("Error while extracting conference records from db", e);
		} finally {
			closeAutoclosable(rs);
		}
		
		return new Page<ConferenceDto>(list, sort, begin, totalRows, Constants.PAGE_SIZE);

	}

	@Override
	public Conference create(Conference conference) {
		ResultSet rs = null;

		try (PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT_NEW_CONFERENCE,
				Statement.RETURN_GENERATED_KEYS)) {

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

		return null;
	}

	@Override
	public List<Conference> findAll() {
		
		Map<Long, Conference> conferences = new LinkedHashMap<>();
		Map<Long, User> visitors = new HashMap<>();
		Map<Long, User> speakers = new HashMap<>();
		Map<Long, Report> reports = new HashMap<>();

		ResultSet rs = null;
		
		try (Statement stmt = connection.createStatement()) {
			
			rs = stmt.executeQuery(SQL_FIND_ALL);

			ConferenceMapper conferenceMapper = new ConferenceMapper();
			ReportMapper reportMapper = new ReportMapper();
			SpeakerMapper speakerMapper = new SpeakerMapper();
			VisitorMapper visitorMapper = new VisitorMapper();

			Conference conference = null;
			Report report = null;
			User speaker = null;
			User visitor = null;

			while (rs.next()) {
								
				System.out.println(rs.getLong("conf_id"));
				
				conference = conferenceMapper.extractFromResultSet(rs);
				conference = conferenceMapper.makeUnique(conferences, conference);
				
				if (rs.getString("report_id") != null) {
					report = reportMapper.extractFromResultSet(rs);
					report = reportMapper.makeUnique(reports, report);
					
					if (rs.getString("speaker_id") != null) {
						speaker = speakerMapper.extractFromResultSet(rs);
						speaker = speakerMapper.makeUnique(speakers, speaker);
						report.setSpeaker(speaker);						
					}
					report.setConference(conference);
					conference.getReports().add(report);
				}
				
				if (rs.getString("visitor_id") != null) {
					visitor = visitorMapper.extractFromResultSet(rs);
					visitor = visitorMapper.makeUnique(visitors, visitor);
					conference.getVisitors().add(visitor);
				}


			}

		} catch (SQLException e) {
			logger.error("Error while extracting conference records from db", e);
		} finally {
			closeAutoclosable(rs);
		}
		
		return new ArrayList<Conference>(conferences.values());
	}

	@Override
	public void update(Conference entity) {

	}

	@Override
	public void delete(long id) {

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
