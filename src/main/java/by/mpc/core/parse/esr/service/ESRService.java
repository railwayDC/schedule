package by.mpc.core.parse.esr.service;

import java.util.LinkedList;
import java.util.List;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.model.StationColumnEnum;

public class ESRService {
	private ListStation listStation;
	
	public ESRService(ListStation listStation) {
		this.listStation = listStation;
	}
	
	public QuickSearch getQuickSearch(StationColumnEnum keyColumn, StationColumnEnum valueColumn) {
		return new QuickSearch(listStation, keyColumn, valueColumn);
	}
	
	public ListStation filter(IModifier modifier) {
		return modifier.filter(listStation);
	}
	
	public List<String> select(StationColumnEnum column) {
		List<String> list = new LinkedList<>();
		for (int i = 0; i < listStation.size(); i++) {
			list.add(listStation.get(i).getParam(column));
		}
		return list;
	}
}
