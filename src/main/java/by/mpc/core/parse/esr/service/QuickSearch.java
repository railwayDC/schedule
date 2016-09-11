package by.mpc.core.parse.esr.service;

import java.util.HashMap;
import java.util.Map;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.model.Station;
import by.mpc.core.parse.esr.model.StationColumnEnum;

public class QuickSearch {
	private Map<String, String> map;

	public QuickSearch(ListStation listStation, StationColumnEnum keyColumn, StationColumnEnum valueColumn) {
		if (listStation == null) {
			throw new IllegalArgumentException();
		}
		map = new HashMap<>();
		Station station;
		for (int i = 0; i < listStation.size(); i++) {
			station = listStation.get(i);
			String key = station.getParam(keyColumn).toLowerCase();
			String value = station.getParam(valueColumn).toLowerCase();
			map.put(key, value);
		}
	}
	
	public String getValue(String key) {
		return map.get(key.toLowerCase());
	}
}
