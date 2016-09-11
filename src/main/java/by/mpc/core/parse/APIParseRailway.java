package by.mpc.core.parse;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import by.mpc.core.model.ListRoute;
import by.mpc.core.parse.esr.core.ESRManager;
import by.mpc.core.parse.esr.model.StationColumnEnum;
import by.mpc.core.parse.esr.service.QuickSearch;

public class APIParseRailway implements IParseRailway {

	private static final String URL_YANDEX_API = "https://api.rasp.yandex.net/v1.0/search/?" + "apikey=%1$s"
			+ "&format=%2$s" + "&from=%3$s" + "&to=%4$s" + "&lang=%5$s" + "&date=%6$s" + "&transport_types=%7$s"
			+ "&system=%8$s" + "&page=%9$s";

	private String apikey;
	private String format;
	private String lang;
	private String transport_types;
	private String system;
	private String date;
	
	private QuickSearch quickSearch;

	public APIParseRailway(String date) {
		ConfigurationAPI config = new ConfigurationAPI();
		apikey = config.getProperty("yandex.api.apikey");
		format = config.getProperty("yandex.api.format");
		lang = config.getProperty("yandex.api.lang");
		transport_types = config.getProperty("yandex.api.transport_types");
		system = config.getProperty("yandex.api.system");
		this.date = date;
		ESRManager manager = new ESRManager();
		quickSearch = manager.getESRService().getQuickSearch(StationColumnEnum.NAME, StationColumnEnum.ESR);
	}

	@Override
	public ListRoute get(String bStation, String eStation) {
		Calendar sTime = Calendar.getInstance();
		
		ListRoute listRoute = null;
		String esrBStation = quickSearch.getValue(bStation);
		String esrEStation = quickSearch.getValue(eStation);
		String urlName = String.format(URL_YANDEX_API, apikey, format, esrBStation, esrEStation, lang, date, transport_types,
				system, 1);
		XMLParseRailway parse = null;
		try (InputStream is = new URL(urlName).openStream()) {
			parse = new XMLParseRailway(is);
			listRoute = parse.get(bStation, eStation);
		} catch (MalformedURLException e) {
			System.err.println("Ошибка подключения к URL-адресу: " + e);
		} catch (IOException e) {
			System.err.println("Ошибка ввода/вывода:  " + e);
		}
		
		Calendar eTime = Calendar.getInstance();
		System.out.println((eTime.getTimeInMillis() - sTime.getTimeInMillis()) / 1000);
		
		return listRoute;
	}
}
