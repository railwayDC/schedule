package by.mpc.core.correct;

import java.util.Calendar;
import java.util.Map;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

/**
 * Класс для проверки времени на станции
 * 
 * @author SQL
 *
 */
public class DurationCorrect extends AbstractCorrect {

	// Карта соответствий названии станции и длительности
	private Map<String, Integer> durations;

	public DurationCorrect(Map<String, Integer> durations) {
		this.durations = durations;
	}

	@Override
	public boolean is(ListRoute trace, Route route) {
		String station = route.getBStation().toLowerCase();
		// Если нет предыдущих станций
		if (trace.isEmpty()) {
			return true;
		}
		if (durations.containsKey(station)) {
			// Вычисляем текущее время нахождения на станции
			Calendar sTime = trace.get(trace.size() - 1).getETime(), eTime = route.getBTime();
			long diff = (eTime.getTimeInMillis() - sTime.getTimeInMillis()) / 60000;
			// Указанное время
			int countMinute = durations.get(station);
			if (diff < countMinute) {
				return false;
			}
		}
		return true;
	}
}
