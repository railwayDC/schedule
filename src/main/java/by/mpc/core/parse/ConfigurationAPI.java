package by.mpc.core.parse;

import java.util.ResourceBundle;

public class ConfigurationAPI {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("api");
	
	public String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
