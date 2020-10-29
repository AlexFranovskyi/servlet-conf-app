package ua.conference.servletapp.model.entity;

import java.time.LocalDateTime;

public class Conference {
	
	private long id;
	private String conferenceName;

	private LocalDateTime localDateTime;
	private String location;

	private int visitorCounter;
	private int reportCounter;

	private int arrivedVisitorsAmount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConferenceName() {
		return conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getVisitorCounter() {
		return visitorCounter;
	}

	public void setVisitorCounter(int visitorCounter) {
		this.visitorCounter = visitorCounter;
	}

	public int getReportCounter() {
		return reportCounter;
	}

	public void setReportCounter(int reportCounter) {
		this.reportCounter = reportCounter;
	}

	public int getArrivedVisitorsAmount() {
		return arrivedVisitorsAmount;
	}

	public void setArrivedVisitorsAmount(int arrivedVisitorsAmount) {
		this.arrivedVisitorsAmount = arrivedVisitorsAmount;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private Conference conference;
		
		private Builder() {
			conference = new Conference();
		}
		
		public Builder id(long id) {
			conference.id = id;
			return this;
		}
		
		public Builder conferenceName(String name) {
			conference.conferenceName = name;
			return this;
		}
		
		public Builder localDateTime(LocalDateTime localDateTime) {
			conference.localDateTime = localDateTime;
			return this;
		}
		
		public Builder location(String location) {
			conference.location = location;
			return this;
		}
		
		public Builder visitorCounter(int visitorCounter) {
			conference.visitorCounter = visitorCounter;
			return this;
		}
		
		public Builder reportCounter(int reportCounter) {
			conference.reportCounter = reportCounter;
			return this;
		}
		
		public Builder arrivedVisitorsAmount(int arrivedVisitorsAmount) {
			conference.arrivedVisitorsAmount = arrivedVisitorsAmount;
			return this;
		}
		
		public Conference build() {
			return conference;
		}
	}

}
