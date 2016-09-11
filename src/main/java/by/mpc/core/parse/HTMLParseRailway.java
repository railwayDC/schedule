package by.mpc.core.parse;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public class HTMLParseRailway implements IParseRailway {
	
	// URL паттерн
	private final String URL_RAILWAY = "http://rasp.rw.by/ru/route/?from=%1$s&to=%2$s&date=%3$s";
	private String date;
	
	// Теги для парсинга
	private final String TAG_START_TIME = "b.train_start-time";
	private final String TAG_END_TIME = "b.train_end-time";
	private final String TAG_START_PLACE = "a.train_start-place";
	private final String TAG_END_PLACE = "a.train_end-place";
	// Максимальное время соединения
	private static final int TIMEOUT = 40000;

	public HTMLParseRailway(String date) {
		this.date = date;
	}
	
	public ListRoute get(String bStation, String eStation) {
	
		Calendar sTime = Calendar.getInstance();
		
		ListRoute routes = new ListRoute();
		try {
			// Грузим HTML-страницу по URL_RAILWAY адресу с указанными параметрами
			Document doc = Jsoup.connect(String.format(URL_RAILWAY, bStation, eStation, date)).timeout(TIMEOUT).get();
			// Получаем элементы из HTML-страницы
			Elements bTimeElements = doc.select(TAG_START_TIME);
			Elements eTimeElements = doc.select(TAG_END_TIME);
			Elements bPlaceElements = doc.select(TAG_START_PLACE);
			Elements ePlaceElements = doc.select(TAG_END_PLACE);
			// Выделяем информацию из элементов
			Properties properties = new Properties();
			properties.setProperty("input.station_start", bStation);
			properties.setProperty("input.station_end", eStation);
			for (int i = 0; i < bTimeElements.size(); i++) {
				
				String bDateTime = getDateTime(date, bTimeElements.get(i).text());
				String eDateTime = getDateTime(date, eTimeElements.get(i).text());
				
				properties.setProperty("input.time_start", bDateTime);
				properties.setProperty("input.time_end", eDateTime);
				properties.setProperty("station_start", bPlaceElements.get(i).text());
				properties.setProperty("station_end", ePlaceElements.get(i).text());				
				
				routes.add(new Route(properties));
			}
		} catch (ParseException e) {
			System.err.println("Ошибка парсинга: " + e);
		} catch (IOException e) {
			System.err.println("Ошибка ввода/вывода: " + e);
		}

		Calendar eTime = Calendar.getInstance();
		System.out.println((eTime.getTimeInMillis() - sTime.getTimeInMillis()) / 1000);
		
		return routes;
	}

	// Определение даты
	private String getDateTime(String date, String time) {
		String[] months = { "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября",
				"октября", "ноября", "декабря" };
		if (time.contains(",")) {
			// Выделение дня, месяца и года
			int day = 0, month = 0, year = 0;
			int comma = time.indexOf(","), space = time.trim().lastIndexOf(" ");
			day = Integer.valueOf(time.substring(comma + 1, space).trim());
			String sMonth = time.substring(space, time.length()).trim();
			for (int i = 0; i < months.length; i++) {
				if (months[i].startsWith(sMonth)) {
					month = i + 1;
				}
			}
			year = Integer.valueOf(date.substring(0, date.indexOf("-")));
			return year + "-" + month + "-" + day + " " + time;
		}
		return date + " " + time;
	}
}
