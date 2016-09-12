package by.mpc.web.command.client;

import by.mpc.web.command.EmailCommand;
import by.mpc.web.command.ICommand;
import by.mpc.web.command.SearchCommand;

public enum CommandEnum {
	SEARCH {
		{
			this.command = new SearchCommand();
		}
	}, 
	EMAIL {
		{
			this.command = new EmailCommand();
		}
	};	
	ICommand command;
	
	public ICommand getCommand() {
		return command;
	}
}
