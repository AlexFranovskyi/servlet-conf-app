package ua.conference.servletapp.model.service;

import java.util.Optional;

import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dao.UserDao;
import ua.conference.servletapp.model.entity.User;

public class RegistrationService {
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public Optional<User> findUserByUsername(String username) {
		UserDao dao = daoFactory.createUserDao();
		Optional<User> opt = dao.findByName(username);
		dao.close();
		return opt;
	}
	
	public boolean createUser(String name, String email, String pass, String role) {
		UserDao dao = daoFactory.createUserDao();
		boolean res = true;
		
		User newUser = User.builder().username(name)
				.email(email)
				.password(pass)
				.role(User.Role.valueOf(role))
				.build();
		
		dao.create(newUser);
		if (newUser.getId() == 0) {
			res = false;
		}
		dao.close();
		return res;
	}

}
