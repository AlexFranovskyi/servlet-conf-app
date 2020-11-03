package ua.conference.servletapp.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import ua.conference.servletapp.model.dto.ReportDto;

public class ReportDtoMapper implements ObjectMapper<ReportDto>{

	@Override
	public ReportDto extractFromResultSet(ResultSet rs) throws SQLException {
		return ReportDto.builder()
				.id(rs.getLong("report_id"))
				.name(rs.getString("report_name"))
				.speakerName(rs.getString("speaker_name"))
				.conferenceName(rs.getString("conference_name"))
				.approved(rs.getBoolean("is_approved"))
				.build();
	}

	@Override
	public ReportDto makeUnique(Map<Long, ReportDto> cache, ReportDto reportDto) {
		cache.putIfAbsent(reportDto.getId(), reportDto);
		return cache.get(reportDto.getId());
	}

}
