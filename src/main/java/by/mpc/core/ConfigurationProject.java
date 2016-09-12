package by.mpc.core;

import java.util.ResourceBundle;

public class ConfigurationProject {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("project");
	public String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
