package ua.conference.servletapp.model.service;

import java.util.Optional;

import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dao.UserDao;
import ua.conference.servletapp.model.entity.User;

public class RegistrationService {
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public User findUserByUsername(String username) {
		UserDao dao = daoFactory.createUserDao();
		User user = dao.findByName(username).get();
		dao.close();
		return user;
	}
	
	public User createUser(User user, String role) {
		UserDao dao = daoFactory.createUserDao();
		
//		Optional<User> userFromDb = dao.findByNameOrEmail(user.getUsername(), user.getEmail());
//		if (userFromDb.isPresent()) {
//			return user;
//		}
		
		user.setRole(User.Role.valueOf(role));		
		dao.create(user);
		dao.close();
		return user;
	}

}
