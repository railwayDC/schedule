package by.mpc.core.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * Класс для описания маршрута
 * @author SQL
 *
 */
public class Route implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm";
	
	protected final String bStation;
	protected final String eStation;
	protected final String bEnterStation;
	protected final String eEnterStation;
	
	protected final Calendar bDateTime;
	protected final Calendar eDateTime;
	
	protected final String numberTrain;
	protected final String typeTrain;

	public Route(Properties properties) throws ParseException {
		/**
		 * Записываем данные из properties
		 */
		// Название станций
		this.bEnterStation = properties.getProperty("input.station_start");
		this.eEnterStation = properties.getProperty("input.station_end");
		// Название станций после парсинга
		this.bStation = properties.getProperty("station_start");
		this.eStation = properties.getProperty("station_end");
		// Время и дата
		String bDateTime = properties.getProperty("input.time_start");
		String eDateTime = properties.getProperty("input.time_end");
		// Номер и тип поезда
		this.numberTrain = properties.getProperty("input.number_train");
		this.typeTrain = properties.getProperty("input.type_train");
		// Преобразование времени и даты в тип Calendar
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		this.bDateTime = GregorianCalendar.getInstance();
		this.bDateTime.setTime(format.parse(bDateTime));
		this.eDateTime = GregorianCalendar.getInstance();
		this.eDateTime.setTime(format.parse(eDateTime));
	}
	
	public Route(Route route) {
		this.bEnterStation = route.bEnterStation;
		this.eEnterStation = route.eEnterStation;
		// Название станций после парсинга
		this.bStation = route.bStation;
		this.eStation = route.eStation;
		// Номер и тип поезда
		this.numberTrain = route.numberTrain;
		this.typeTrain = route.typeTrain;
		// Преобразование времени и даты в тип Calendar
		this.bDateTime = route.bDateTime;
		this.eDateTime = route.eDateTime;
	}

	public String getBStation() {
		return bStation;
	}

	public String getEStation() {
		return eStation;
	}
	
	public String getNumberTrain() {
		return numberTrain;
	}

	public String getTypeTrain() {
		return typeTrain;
	}

	public String getBEnterStation() {
		return bEnterStation;
	}

	public String getEEnterStation() {
		return eEnterStation;
	}
	
	public Calendar getBTime() {
		return bDateTime;
	}

	public Calendar getETime() {
		return eDateTime;
	}
	
	// Строковое представления даты
	private String get(Calendar c, String f) {
		SimpleDateFormat format = new SimpleDateFormat(f);
		return format.format(c.getTime());			
	}
	
	public String getETimeString(String pattern) {
		return get(eDateTime, pattern);
	}
	
	
	public String getBTimeString(String pattern) {
		return get(bDateTime, pattern);
	}
	
	@Override
	public String toString() {
		return String.format("%3$s - %4$s %1$s - %2$s", bStation, eStation, getBTimeString(TIME_FORMAT), getETimeString(TIME_FORMAT));
	}
}
