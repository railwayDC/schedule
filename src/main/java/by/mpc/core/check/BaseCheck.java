package by.mpc.core.check;

import java.util.ArrayList;
import java.util.List;

import by.mpc.core.model.ListRoute;

public class BaseCheck extends AbstractCheck {
	@Override
	public boolean is(ListRoute trace) {
		if (trace == null) {
			return false;
		}
		// Количество маршрутов
		int size = trace.size();
		if (size == 0) {
			return false;
		}
		// Проверка, что прибыли на конечную станцию
		String eEnterTraceStation = trace.get(size - 1).getEEnterStation();
		if (!eEnterStation.equals(eEnterTraceStation)) {
			return false;
		}
		// Посещение всех станций
		List<String> st = new ArrayList<String>();
		String station;
		for (int i = 0; i < trace.size(); i++) {
			station = trace.get(i).getBEnterStation();
			if (!st.contains(station)) {
				st.add(station);
			}
			station = trace.get(i).getEEnterStation();
			if (!st.contains(station)) {
				st.add(station);
			} else {
				// Промежуточные станции не должны повторяться
				if (!station.equals(bEnterStation) && !station.equals(eEnterStation)) {
					return false;
				}
			}
			
		}
		int count = stations.size() + 1;
		if (!bEnterStation.equals(eEnterStation)) {
			count++;
		}
		if (count != st.size()) {
			return false;
		}
		return true;
	}
}
