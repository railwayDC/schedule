package by.mpc.core.correct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

/**
 * Временное ограничение
 * @author SQL
 *
 */
public class TimeCorrect extends AbstractCorrect {

	private int hour;
	private int minute;
	private String date;
	// Ограничение сверху или снизу
	private boolean isMore;

	public TimeCorrect(String time, String date, boolean isMore) throws ParseException {
		// Выделяем hour и minute из time
		SimpleDateFormat format = new SimpleDateFormat(Route.TIME_FORMAT);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(format.parse(time));
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		// Записываем остальные переменные
		this.date = date;
		this.isMore = isMore;
	}

	@Override
	public boolean is(ListRoute trace, Route route) {
		// Проверка времени
		int h, m;
		if (isMore) {
			h = route.getETime().get(Calendar.HOUR_OF_DAY);
			m = route.getETime().get(Calendar.MINUTE);
			if (h > hour || (h == hour && m > minute)) {
				return false;
			}
		} else {
			h = route.getBTime().get(Calendar.HOUR_OF_DAY);
			m = route.getBTime().get(Calendar.MINUTE);
			if (h < hour || (h == hour && m < minute)) {
				return false;
			}
		}
		// Проверка даты
		String date = route.getETimeString(Route.DATE_FORMAT);
		if (!this.date.equals(date)) {
			return false;
		}
		return true;
	}
}
