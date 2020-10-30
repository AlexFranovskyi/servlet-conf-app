package ua.conference.servletapp.model.dao;

import java.util.Optional;

import ua.conference.servletapp.model.entity.User;

public interface UserDao extends GenericDao<User>{
	
	Optional<User> findByName(String name);

}
