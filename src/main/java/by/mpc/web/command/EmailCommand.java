package by.mpc.web.command;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import by.mpc.tools.email.MailThread;

public class EmailCommand implements ICommand {

	private static final String MAIL_SUBJECT = "Расписание движения поездов";

	@Override
	public String execute(HttpServletRequest request) {
		// Загружаем свойства из файла
		Properties properties = new Properties();
		try {
			ServletContext context = request.getServletContext();
			properties.load(context.getResourceAsStream("email"));
		} catch (IOException e) {
			System.err.println("Ошибка инициализации свойств: " + e);
		}
		MailThread mailOperator = new MailThread(request.getParameter("email"), MAIL_SUBJECT,
				request.getParameter("msg"), properties);
		mailOperator.start();

		return "/WEB-INF/jsp/index.jsp";
	}

}
