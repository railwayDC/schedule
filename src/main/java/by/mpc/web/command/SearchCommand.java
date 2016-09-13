package by.mpc.web.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.mpc.core.ManagerRailway;

public class SearchCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request) {
		
		String bStation = request.getParameter("bStation"), eStation = request.getParameter("eStation"),
				date = request.getParameter("date"), bTime = request.getParameter("bTime"),
				eTime = request.getParameter("eTime");
		if (bStation == null || eStation == null || date == null || bTime == null || eTime == null) {
			return "/WEB-INF/jsp/error.jsp";
		}
		Map<String, Object> params = new HashMap<>();
		params.put("input.date", date);
		params.put("input.time_start", bTime);
		params.put("input.time_end", eTime);
		params.put("input.station_start", bStation);
		params.put("input.station_end", eStation);

		List<String> list = new ArrayList<String>();
		Map<String, Integer> durations = new HashMap<>();

		String[] s = request.getParameterValues("s");
		String[] d = request.getParameterValues("d");
		if (s != null) {
			for (int i = 0; i < s.length; i++) {
				String station = s[i].toLowerCase();
				int duration = Integer.valueOf(d[i]);
				if (station != "") {
					durations.put(station, duration);
					list.add(station);
				}
			}
		}

		params.put("input.stations", list);
		params.put("input.durations", durations);

		ManagerRailway manager = new ManagerRailway(params);
		manager.start();
		try {
			manager.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		request.setAttribute("schedule", manager.schedule());
		
		return "WEB-INF/jsp/index.jsp";
	}
}
