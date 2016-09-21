package by.mpc.web.view.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public class ListRouteView {

	private List<RouteView> list;

	// Дополнительные параметры
	private RouteView externRoute;

	public ListRouteView(ListRoute listRoute) {
		list = new ArrayList<RouteView>();
		// Добавляем дополнительную информацию в маршруты
		Route prevRoute = null;
		for (Route route : listRoute.getList()) {
			if (prevRoute != null) {
				list.add(new RouteView(prevRoute, route));
			}
			prevRoute = route;
		}
		list.add(new RouteView(prevRoute, null));
		// Создаем внешний маршрут на базе списка маршрутов
		calcExternRoute();
	}
	
	// Рассчет внешнего маршрута
	private void calcExternRoute() {
		int size = list.size();
		if (size == 0) {
			return;
		}
		try {
			// Заполняем внешний маршрут свойствами
			Properties properties = new Properties();
			properties.setProperty("input.station_start", list.get(0).getBEnterStation());
			properties.setProperty("input.station_end", list.get(size - 1).getBEnterStation());
			properties.setProperty("station_start", list.get(0).getBStation());
			properties.setProperty("station_end", list.get(size - 1).getEStation());
			properties.setProperty("input.time_start", list.get(0).getBTimeString(Route.DATE_TIME_FORMAT));
			properties.setProperty("input.time_end", list.get(size - 1).getETimeString(Route.DATE_TIME_FORMAT));
			properties.setProperty("input.number_train", String.valueOf(size));
			// Создаем маршрут
			Route route = new Route(properties);
			externRoute = new RouteView(route);
			// Заполняем маршрут дополнительными свойствами (время)
			int stationTime = 0,
				trainTime = 0,
				totalTime = 0;
			
			for (RouteView r : list) {
				stationTime += r.getStationTime();
				trainTime += r.getTrainTime();
			}
			totalTime = stationTime + trainTime;
			externRoute.setStationTime(stationTime);
			externRoute.setTrainTime(trainTime);
			externRoute.setTotalTime(totalTime);
		} catch (ParseException ex) {
			System.err.println("Ошибка парсинга даты: " + ex);
			ex.printStackTrace();
		}
	}

	public List<RouteView> getList() {
		return Collections.unmodifiableList(list);
	}

	public RouteView getExternRoute() {
		return externRoute;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("externRoute - " + externRoute + " endExternRoute\n");
		for (int i = 0; i < list.size(); i++) {
			s.append(list.get(i) + "\n");
		}
		return s.toString();
	}

}
