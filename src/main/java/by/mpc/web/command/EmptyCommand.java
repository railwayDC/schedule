package by.mpc.web.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request) {
		return "WEB-INF/jsp/index.jsp";
	}

}
