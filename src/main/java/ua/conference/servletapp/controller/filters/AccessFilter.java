package ua.conference.servletapp.controller.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.conference.servletapp.model.entity.User;

public class AccessFilter implements Filter {
	
	final static Logger logger = LogManager.getLogger(AccessFilter.class);
	
	private static Map<User.Role, List<String>> accessMap;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String path = req.getRequestURI();
		
		path = path.replaceAll(".*/servlet-conf-app/", "");
		if ("".equals(path) || accessMap.get(session.getAttribute("role")).contains(path)) {
			chain.doFilter(request, response);
			logger.info("Access to command is allowed");
		} else {
			logger.info("Access to command is denied");
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
		accessMap = new HashMap<>();
		accessMap.put(User.Role.ADMIN, toList(filterConfig.getInitParameter("admin")));
		accessMap.put(User.Role.GUEST, toList(filterConfig.getInitParameter("guest")));
		
		
	}
	
	private List<String> toList(String str) {
		List<String> list = new ArrayList<>();
		String[] array = str.split(", ");
		for (String command : array) {
			list.add(command);
		}
		return list;
	}

}
