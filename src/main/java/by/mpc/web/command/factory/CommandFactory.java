package by.mpc.web.command.factory;

import javax.servlet.http.HttpServletRequest;

import by.mpc.web.command.EmptyCommand;
import by.mpc.web.command.ICommand;
import by.mpc.web.command.client.CommandEnum;

public class CommandFactory {
	public ICommand defineCommand(HttpServletRequest request) {
		ICommand command = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return command;
		}
		CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
		command = currentEnum.getCommand();
		return command;
	}
}
