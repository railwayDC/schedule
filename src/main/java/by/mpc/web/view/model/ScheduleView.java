package by.mpc.web.view.model;

import java.util.ArrayList;
import java.util.List;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Schedule;

public class ScheduleView {
	private List<ListRouteView> schedule;
	
	public ScheduleView() {
		this.schedule = new ArrayList<ListRouteView>();
	}
	
	public ScheduleView(Schedule schedule)  {
		this();
		for (ListRoute listRoute : schedule.getList()) {
			this.schedule.add(new ListRouteView(listRoute));
		}
	}
	
	public int size() {
		return schedule.size();
	}
	
	public List<ListRouteView> getList() {
		return schedule;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < schedule.size(); i++) {
			s.append("#" + (i + 1) + "\n");
			s.append(schedule.get(i) + "\n");
		}
		return s.toString();
	}
}
