package by.mpc.core;

import java.util.Calendar;

import by.mpc.core.check.ListCheck;
import by.mpc.core.correct.ListCorrect;
import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;
import by.mpc.core.model.Schedule;

/**
 * Класс для поиска маршрутов
 * 
 * @author SQL
 *
 */
public class SearchRailway {

	// Класс хранящий первоначальные маршруты
	private ListRoute listRoute;
	// Класс для хранения итогового расписания
	private final Schedule schedule;
	// Класс для проверки корректности значений
	private final ListCheck check;
	private final ListCorrect correct;
	private final Calendar dateTime;
	
	private int MAX_SELECT = 100;
	
	public SearchRailway(ListRoute listRoute, Calendar dateTime, ListCheck check, ListCorrect correct) {
		this(dateTime, check, correct);
		this.listRoute = listRoute;
	}

	public SearchRailway(Calendar dateTime, ListCheck check, ListCorrect correct) {
		this.dateTime = dateTime;
		this.check = check;
		this.correct = correct;
		schedule = new Schedule();
	}

	public void setListTrace(ListRoute listRoute) {
		this.listRoute = listRoute;
	}

	public Schedule getSchedule() {
		if (listRoute == null) {
			throw new IllegalArgumentException();
		}
		schedule.clearAll();
		// Рекурсивная функция для поиска маршрутов
		search(null, (String)Data.getInstance().getParam("input.station_start"), dateTime);
		return schedule;
	}
	
	private void search(ListRoute trace, String station, Calendar dateTime) {
		if (MAX_SELECT <= 0) {
			return;
		}
		// Добавление текущего маршрута
		if (trace == null) {
			trace = new ListRoute();
		}
		// Маршруты к другим станция
		ListRoute list = FilterRoute.get(listRoute, station, dateTime);
		for (int i = 0; i < list.size(); i++) {
			ListRoute t = new ListRoute(trace);
			Route r = list.get(i);
			// Добавляем новые пути и проверяем их корректность
			if (correct.is(t, r)) {
				t.add(r);
				if (check.is(t)) {
					MAX_SELECT--;
					schedule.add(t);
					if (MAX_SELECT <= 0) {
						break;
					}
				} else {
					search(t, r.getEEnterStation(), r.getETime());
				} 
			}
		}
	}
}
