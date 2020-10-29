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
	
	public boolean createUser(User user) {
		UserDao dao = daoFactory.createUserDao();
		boolean res = true;
		
		dao.create(user);
		if (user.getId() == 0) {
			res = false;
		}
		dao.close();
		return res;
	}

}
