package by.mpc.core.correct;

import java.util.ArrayList;
import java.util.List;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public class ListCorrect {
	private List<ICorrect> list;
	
	public ListCorrect() {
		list = new ArrayList<ICorrect>();
	}
	
	public void register(ICorrect correct) {
		list.add(correct);
	}
	
	public void remove(ICorrect correct) {
		list.remove(correct);
	}
	
	public boolean is(ListRoute trace, Route route) {
		for (ICorrect c : list) {
			if (!c.is(trace, route)) {
				return false;
			}
		}
		return true;
	}
}
