package by.mpc.core.parse.esr.core;

import java.util.ResourceBundle;

public class ConfigurationESR {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("esr");
	public String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
