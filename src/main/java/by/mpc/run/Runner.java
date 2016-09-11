package by.mpc.run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.mpc.core.ManagerRailway;

public class Runner {
	public static void main(String[] args) throws IOException {

		Map<String, Object> params = new HashMap<>();
		params.put("input.date", "2016-09-11");
		params.put("input.time_start", "09:48");
		params.put("input.time_end", "17:00");
		params.put("input.station_start", "Минск");
		params.put("input.station_end", "Минск");
		
		List<String> list = new ArrayList<String>();
		list.add("Озерище");
		list.add("Городище");
		list.add("Колодищи");
		params.put("input.stations", list);
		
		Map<String, Integer> durations = new HashMap<>();
		durations.put("озерище", 30);
		durations.put("городище", 30);
		durations.put("колодищи", 30);
		params.put("input.durations", durations);
		
		ManagerRailway manager = new ManagerRailway(params);
		manager.start();
		try {
			manager.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(manager.schedule());
	}
}
