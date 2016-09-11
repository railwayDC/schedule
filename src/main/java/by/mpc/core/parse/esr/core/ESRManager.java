package by.mpc.core.parse.esr.core;

import java.io.IOException;
import java.util.Properties;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.service.ESRService;

public class ESRManager {
	private ListStation listStation;
	
	public ESRManager() {
		try {
			ConfigurationESR config = new ConfigurationESR();
			String filePath = config.getProperty("database.filePath");
			String text = File.read(filePath);
			
			Properties properties = new Properties();
			properties.setProperty("database.pattern", config.getProperty("database.pattern"));
			listStation = ESRLoader.getListStation(text, properties);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ESRService getESRService() {
		if (listStation == null) {
			throw new IllegalArgumentException();
		}
		return new ESRService(listStation);
	}
}
