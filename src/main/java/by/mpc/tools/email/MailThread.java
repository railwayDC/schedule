package by.mpc.tools.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mail.creator.SessionCreator;

public class MailThread extends Thread {

	private MimeMessage message;
	private String sendToEmail;
	private String mailSubject;
	private String mailText;
	private Properties properties;

	public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
		this.sendToEmail = sendToEmail;
		this.mailSubject = mailSubject;
		this.mailText = mailText;
		this.properties = properties;
	}

	private void init() {
		// Создаем почтовую сессию
		Session mailSession = (new SessionCreator(properties)).createSession();
		mailSession.setDebug(true);
		// Создаем сообщение
		message = new MimeMessage(mailSession);
		try {
			message.setSubject(mailSubject);
			message.setText(mailText);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
		} catch (AddressException e) {
			System.err.println("Некорректный адрес: " + sendToEmail + " " + e);
		} catch (MessagingException e) {
			System.err.println("Ошибка формирование сообщеня: " + e);
		}
	}

	@Override
	public void run() {
		init();
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
