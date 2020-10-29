package ua.conference.servletapp.model.entity;

public class Report {

	private long id;

	private String name;

	private String conferenceName;

	private String speakerName;

	private boolean approved;

	public Report(String name) {
		this.name = name;
	}
	
	public Report(String name, String conferenceName) {
		this.name = name;
		this.conferenceName = conferenceName;
	}

	public Report(String name, String conferenceName, String speakerName) {
		this.name = name;
		this.conferenceName = conferenceName;
		this.speakerName = speakerName;
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

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
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
		
		public Builder id(long id) {
			report.id = id;
			return this;
		}
		
		public Builder name(String name) {
			report.name = name;
			return this;
		}
		
		public Builder conferenceName(String conferenceName) {
			report.conferenceName = conferenceName;
			return this;
		}
		
		public Builder speakerName(String speakerName) {
			report.speakerName = speakerName;
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
