package ua.conference.servletapp.model.entity;

public class Report {

	private long id;
	private String name;
	
	private Conference conference;
	private User speaker;
	
	private boolean approved;
	
	public Report() {
	}

	public Report(String name) {
		this.name = name;
	}
	
	public Report(String name, Conference conference) {
		this.name = name;
		this.conference = conference;
	}

	public Report(String name, Conference conference, User speaker) {
		this.name = name;
		this.conference = conference;
		this.speaker = speaker;
	}

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

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public User getSpeaker() {
		return speaker;
	}

	public void setSpeaker(User speaker) {
		this.speaker = speaker;
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
	
	public static class Builder{
		private Report report;
		
		private Builder() {
			report = new Report();
		}
		
		public Builder id(long id) {
			report.id = id;
			return this;
		}
		
		public Builder name(String name) {
			report.name = name;
			return this;
		}
		
		public Builder conference(Conference conference) {
			report.conference = conference;
			return this;
		}
		
		public Builder speaker(User speaker) {
			report.speaker = speaker;
			return this;
		}
		
		public Builder approved(boolean approved) {
			report.approved = approved;
			return this;
		}
		
		public Report build() {
			return report;
		}
		
	}
	
}
