package by.mpc.core.parse.cash;

import java.util.HashMap;
import java.util.Map;

import by.mpc.core.Data;
import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public class CashManager {
	
	private static final String FILEPATH = Data.config.getProperty("project.path") + "/WEB-INF/classes/cash/%1$s";
	
	public static ListRoute load(String date, String station) {
		return Serializator.deserialization(String.format(FILEPATH, date), station);
	}

	public static boolean save(ListRoute listRoute, String date) {
		boolean flag = false;
		if (listRoute == null) {
			throw new IllegalArgumentException();
		}
		Map<String, ListRoute> map = new HashMap<>();
		Route route = null;
		// Перебор listRoute
		for (int i = 0; i < listRoute.size(); i++) {
			route = listRoute.get(i);
			String station = route.getBEnterStation();
			if (!map.containsKey(station)) {
				map.put(station, new ListRoute());
			}
			map.get(station).add(route);
		}
		// Выполняем сохранение по разным файлам
		for (String station : map.keySet()) {
			Serializator.serialization(map.get(station), String.format(FILEPATH, date), station);
		}
		return flag;
	}
}
