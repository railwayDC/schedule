package by.mpc.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.mpc.core.Data;
import by.mpc.web.command.ICommand;
import by.mpc.web.command.factory.CommandFactory;

public class Controller extends HttpServlet {

	/**
	 * 	
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// Записываем путь к проекту в свойство project.path
		Data.config.setProperty("project.path", getServletContext().getRealPath("//"));
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Получаем экземпляр класс command по имени свойства command 
		CommandFactory factory = new CommandFactory();
		ICommand command = factory.defineCommand(request);
		// Выполняем метод класс и получаем страницу
		String page = command.execute(request);
		if (page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

}
