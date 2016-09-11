package by.mpc.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import by.mpc.core.check.BaseCheck;
import by.mpc.core.check.ListCheck;
import by.mpc.core.correct.DurationCorrect;
import by.mpc.core.correct.ListCorrect;
import by.mpc.core.correct.TimeCorrect;
import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;
import by.mpc.core.model.Schedule;
import by.mpc.core.parse.FileParseRailway;
import by.mpc.core.parse.HTMLParseRailway;
import by.mpc.core.parse.ListParseRailway;
import by.mpc.core.parse.cash.CashManager;

/**
 * Главный класс для работы с расписанием
 * 
 * @author SQL
 *
 *			Список параметров
 * input.date 			-	дата;String
 * input.time_start 	-	начальное время;String
 * input.time_end 		- 	конечное время;String
 * input.station_start 	-	начальная станция;String
 * input.station_end 	-	конечная станция;String
 * input.stations 		-	список станций;List<String>
 * input.durations 		- 	список длительностей;Map<String, Integer>
 *
 */

public class ManagerRailway extends Thread {
	
	private Map<String, Object> params;
	private Schedule schedule;
	
	public ManagerRailway(Map<String, Object> params) {
		this.params = params;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		// Получение параметров
		String date = (String) params.get("input.date");
		String sTime = (String) params.get("input.time_start");
		String eTime = (String) params.get("input.time_end");
		Map<String, Integer> durations = (Map<String, Integer>) params.get("input.durations");
		
		// Записываем данные в глобальный объект
		Data data = Data.getInstance();
		data.setParam("input.station_start", (String) params.get("input.station_start"));
		data.setParam("input.station_end", (String) params.get("input.station_end"));
		data.setParam("input.stations", (List<String>) params.get("input.stations"));
		
		try {
			// Ограничения для SearchRailway
			ListCheck check = new ListCheck();
			check.register(new BaseCheck());
			ListCorrect correct = new ListCorrect();
			correct.register(new TimeCorrect(sTime, date, false));
			correct.register(new TimeCorrect(eTime, date, true));
			correct.register(new DurationCorrect(durations));

			// Преобразуем время String -> Calendar
			SimpleDateFormat format = new SimpleDateFormat(Route.DATE_TIME_FORMAT);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(format.parse(date + " " + sTime));
			// Инициализируем список парсеров
			ListParseRailway parse = new ListParseRailway();
			parse.register(new FileParseRailway(date));
			parse.register(new HTMLParseRailway(date));
			
			ListRoute listRoute = getListRoute(parse);
			CashManager.save(listRoute, date);
			
			SearchRailway searchRailway = new SearchRailway(listRoute, calendar, check, correct);
			schedule = searchRailway.getSchedule();
		} catch (ParseException e) {
			System.err.println("Ошибка парсинга: " + e);
		}
	}
	
	// Список маршрутов между станциями
	@SuppressWarnings("unchecked")
	private ListRoute getListRoute(ListParseRailway parse) {
		// Получаем все введенные параметры
		String bStation = (String) params.get("input.station_start");
		String eStation = (String) params.get("input.station_end");
		List<String> stations = (List<String>) params.get("input.stations");
		// Список всех маршрутов для заданных станций
		ListRoute listRoute = new ListRoute();
		// Маршруты оправления с первой станции
		for (int i = 0; i < stations.size(); i++) {
			ListRoute routes = parse.get(bStation, stations.get(i));
			if (routes.size() != 0) {
				listRoute.addAll(routes);
			}
		}
		// Маршруты прибытия на конечную станцию
		for (int i = 0; i < stations.size(); i++) {
			ListRoute routes = parse.get(stations.get(i), eStation);
			if (routes.size() != 0) {
				listRoute.addAll(routes);
			}
		}
		// Маршруты между промежуточными станциями
		for (int i = 0; i < stations.size(); i++) {
			for (int j = 0; j < stations.size(); j++) {
				if (i != j) {
					String iStation = stations.get(i), jStation = stations.get(j);
					ListRoute routes = parse.get(iStation, jStation);
					if (routes.size() != 0) {
						listRoute.addAll(routes);
					}
				}
			}
		}
		if (stations.size() == 0) {
			ListRoute routes = parse.get(bStation, eStation);
			if (routes.size() != 0) {
				listRoute.addAll(routes);
			}
		}
		return listRoute;
	}
	
	public Schedule schedule() {		
		return schedule;
	}
}
