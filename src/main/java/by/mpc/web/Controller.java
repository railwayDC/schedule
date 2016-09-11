package by.mpc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mpc.web.command.ICommand;
import by.mpc.web.command.factory.CommandFactory;

public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" ---------------------- step1 -------------------------- ");
		CommandFactory factory = new CommandFactory();
		System.out.println(" ---------------------- step2 -------------------------- ");
		ICommand command = factory.defineCommand(request);
		System.out.println(" ---------------------- step3 -------------------------- ");
		System.out.println(command);
		String page = command.execute(request);
		System.out.println(" ---------------------- step4 -------------------------- ");
		if (page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

}
