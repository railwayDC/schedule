package by.mpc.core.parse.esr.service;

import java.util.Map;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.model.Station;
import by.mpc.core.parse.esr.model.StationColumnEnum;

public class DeleteModifier implements IModifier {

	private Map<StationColumnEnum, String> map;

	public DeleteModifier(Map<StationColumnEnum, String> map) {
		this.map = map;
	}

	@Override
	public ListStation filter(ListStation listStation) {
		ListStation list = new ListStation();
		Station station;
		for (int i = 0; i < listStation.size(); i++) {
			for (StationColumnEnum column : map.keySet()) {
				String value = map.get(column);
				station = listStation.get(i);
				if (!station.getParam(column).equals(value)) {
					list.add(station);
				}
			}
		}
		return list;
	}

}
