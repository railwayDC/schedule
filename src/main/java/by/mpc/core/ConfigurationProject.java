package by.mpc.core;

import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigurationProject {
	private static Properties properties;
	
	static {
		// Считываем данные с файла и переносим в properties
		ResourceBundle resourceBundle = ResourceBundle.getBundle("project");
		properties = new Properties();
		for (String key : resourceBundle.keySet()) {
			String value = resourceBundle.getString(key);
			properties.setProperty(key, value);
		}
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	public void setProperty(String key, String value) {
		properties.setProperty(key, value);
	}
}
