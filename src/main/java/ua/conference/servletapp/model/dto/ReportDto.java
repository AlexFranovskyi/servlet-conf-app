package ua.conference.servletapp.model.dto;

import java.util.Optional;

import ua.conference.servletapp.model.entity.Conference;
import ua.conference.servletapp.model.entity.User;

public class ReportDto {
	private long id;
	private String name;
	private String speakerName;
	private String conferenceName;
	private boolean approved;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private ReportDto newReportDto;
		
		public Builder() {
			newReportDto = new ReportDto();
		}
		
		public Builder id(long id) {
			newReportDto.id = id;
			return this;
		}
		
		public Builder name(String name) {
			newReportDto.name = name;
			return this;
		}
		
		public Builder speaker(User speaker) {
			newReportDto.speakerName = Optional.ofNullable(speaker)
					.map(user -> user.getUsername()).orElse("");
			return this;
		}
		
		public Builder conferenceName(Conference conference) {
			newReportDto.conferenceName = Optional.ofNullable(conference)
					.map(conf -> conf.getConferenceName()).orElse("");
			return this;			
		}
		
		public Builder approved(boolean approved) {
			newReportDto.approved = approved;
			return this;
		}
		
		public ReportDto build() {
			return newReportDto;
		}
				
	}
}
