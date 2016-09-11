package by.mpc.web.command.client;

import by.mpc.web.command.ICommand;
import by.mpc.web.command.SearchCommand;

public enum CommandEnum {
	SEARCH {
		{
			this.command = new SearchCommand();
		}
	};	
	ICommand command;
	
	public ICommand getCommand() {
		return command;
	}
}
