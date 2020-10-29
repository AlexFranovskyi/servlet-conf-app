package ua.conference.servletapp.model.dto;

public class UserDto {
	
	private long id;
	
	private String username;
	private String email;
	
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private UserDto userDto;
		
		public Builder id(long id) {
			userDto.id = id;
			return this;
		}
		
		public Builder username(String username) {
			userDto.username = username;
			return this;
		}
		
		public Builder email(String email) {
			userDto.email = email;
			return this;
		}
		
		public Builder role(String role) {
			userDto.role = role;
			return this;
		}
	}

}
