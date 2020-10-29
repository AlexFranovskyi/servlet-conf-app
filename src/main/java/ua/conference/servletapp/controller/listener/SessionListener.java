package ua.conference.servletapp.controller.listener;

import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ua.conference.servletapp.model.entity.User;
import ua.conference.servletapp.support.Constants;


public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    	HttpSession session = httpSessionEvent.getSession();
    	session.setAttribute("lang", Constants.DEFAULT_LOCALE);
    	session.setAttribute("username", "Guest");
		session.setAttribute("role", User.Role.GUEST);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String username = (String) httpSessionEvent.getSession()
                .getAttribute("username");
        loggedUsers.remove(username);
        httpSessionEvent.getSession().setAttribute("loggedUsers", loggedUsers);
    }
}
