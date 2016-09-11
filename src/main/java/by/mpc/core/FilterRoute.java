package by.mpc.core;

import java.util.Calendar;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

/**
 * Класс для фильтрации возможных маршрутов
 * @author SQL
 *
 */
public class FilterRoute {
	public static ListRoute get(ListRoute listRoute, String station, Calendar date) {
		ListRoute list = new ListRoute();
		for (Route route : listRoute) {
			if (route.getBEnterStation().equals(station) && date.before(route.getBTime())) {
				list.add(route);
			}
		}
		return list;
	}
}
