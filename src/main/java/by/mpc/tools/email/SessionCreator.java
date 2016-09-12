package by.mpc.tools.email;

import java.util.Properties;

import javax.mail.Session;

public class SessionCreator {
	private String smtpHost;
	private String smtpPort;
	private String userName;
	private String userPassword;
	private Properties sessionProperties;
	
	public SessionCreator(Properties configProperties) {
		userName = configProperties.getProperty("mail.user.name");
		userPassword = configProperties.getProperty("mail.user.password");
		smtpHost = configProperties.getProperty("mail.smtp.host");
		smtpPort = configProperties.getProperty("mail.smtp.port");
		
		sessionProperties = new Properties();
		
		sessionProperties.setProperty("mail.transport.protocol", "smtp");
		sessionProperties.setProperty("mail.host", smtpHost);
		sessionProperties.put("mail.smtp.auth", "true");
		sessionProperties.put("mail.smtp.port", smtpPort);
		sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
		sessionProperties.put("mail.smtp.ssl.enable", "true");
		sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
		sessionProperties.setProperty("mail.smtp.quitwait", "false");
	}
	
	public Session createSession() {
		return Session.getDefaultInstance(sessionProperties, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(userName, userPassword);
			}
		});
	}
}
