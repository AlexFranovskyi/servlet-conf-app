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
