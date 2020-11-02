package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.entity.Report;

public class ReportMapper implements ObjectMapper<Report> {

	@Override
	public Report extractFromResultSet(ResultSet rs) throws SQLException {	
		return Report.builder()
				.id(rs.getLong("report_id"))
				.name(rs.getString("report_name"))
				.approved(rs.getBoolean("is_approved"))
				.build();
	}

	@Override
	public Report makeUnique(Map<Long, Report> cache, Report report) {
		cache.putIfAbsent(report.getId(), report);
		return cache.get(report.getId());
	}

}
