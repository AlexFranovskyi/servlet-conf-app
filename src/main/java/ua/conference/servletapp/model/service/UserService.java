package ua.conference.servletapp.model.service;

import java.util.List;

import ua.conference.servletapp.model.dao.DaoFactory;
import ua.conference.servletapp.model.dao.UserDao;
import ua.conference.servletapp.model.entity.User;


public class UserService {
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public List<User> findAll(){
		UserDao dao = daoFactory.createUserDao();
		List<User> list = dao.findAll();
		dao.close();
		return list;
	}
	
	public User findUserById(long id) {
		UserDao dao = daoFactory.createUserDao();		
		return dao.findById(id).get();
	}

}
