package ua.conference.servletapp.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.conference.servletapp.model.entity.User;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		if( userName == null || userName.equals("") || password == null || password.equals("")  ){
            System.out.println("Not full login data");
            return "redirect:/" + request.getRequestURI();
        }
        System.out.println(userName + " " + password);
        System.out.println("Yes! Login data are full");
            //TODO: check login with DB

        if(CommandUtility.checkUserIsLogged(request, userName)){
            return "/error.jsp";
        }

        //TODO: refine this block
        if (userName.equals("Admin")){
            CommandUtility.setUserNameAndRole(request, User.Role.ADMIN, userName);
            return "redirect:/servlet-conf-app";
        } else if(userName.equals("User")) {
            CommandUtility.setUserNameAndRole(request, User.Role.USER, userName);
            return "redirect:/servlet-conf-app";
        } else {
            CommandUtility.setUserNameAndRole(request, User.Role.GUEST, userName);
            return "redirect:/servlet-conf-app";
        }
	}
}
