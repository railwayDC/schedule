package by.mpc.core.parse.esr.service;

import java.util.HashSet;
import java.util.Set;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.model.Station;
import by.mpc.core.parse.esr.model.StationColumnEnum;

public class SingleModifier implements IModifier {
	@Override
	public ListStation filter(ListStation listStation) {
		ListStation list = new ListStation();
		Set<String> set = new HashSet<>();
		Station station;
		for (int i = 0; i < listStation.size(); i++) {
			station = listStation.get(i);
			String stationName = station.getParam(StationColumnEnum.NAME);
			if (!set.contains(stationName)) {
				set.add(stationName);
				list.add(station);
			}
		}
		return list;
	}
}
