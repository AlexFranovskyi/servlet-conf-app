package ua.conference.servletapp.model.entity;

public class User{

	private long id;
	
	private String username;
	private String email;
	
	private String password;
	private Role role;
	
	
	public User() {
	}

	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public enum Role {
		ADMIN, SPEAKER, USER, GUEST
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder{
		private User user;
		
		private Builder() {
			user = new User();
		}
		
		public Builder id(long id) {
			user.id = id;
			return this;
		}
		
		public Builder username(String usernam) {
			user.username = usernam;
			return this;
		}
		
		public Builder email(String email) {
			user.email = email;
			return this;
		}
		
		public Builder password(String password) {
			user.password = password;
			return this;
		}
		
		public Builder role(Role role) {
			user.role = role;
			return this;
		}
		
		public User build() {
			return user;
		}
	}
}
