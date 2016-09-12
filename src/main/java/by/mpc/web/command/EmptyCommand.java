package by.mpc.web.command;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request) {

		Calendar calendar = Calendar.getInstance();
		// От какого времени
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String bTime = format.format(calendar.getTime());
		// До какого времени
		if (calendar.get(Calendar.HOUR_OF_DAY) > 17) {
			bTime = "08:00";
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		// Дата
		format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(calendar.getTime());

		return "WEB-INF/jsp/index.jsp" + "?bStation=Минск" + "&eStation=Минск" + "&bTime=" + bTime + "&eTime=17:00"
				+ "&date=" + date;
	}

}
