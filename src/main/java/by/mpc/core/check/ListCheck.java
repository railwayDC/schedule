package by.mpc.core.check;

import java.util.ArrayList;
import java.util.List;

import by.mpc.core.model.ListRoute;

public class ListCheck {
	private List<ICheck> list;
	
	public ListCheck() {
		list = new ArrayList<ICheck>();
	}
	
	public void register(ICheck check) {
		list.add(check);
	}
	
	public void remove(ICheck check) {
		list.remove(check);
	}
	
	public boolean is(ListRoute trace) {
		for (ICheck c : list) {
			if (!c.is(trace)) {
				return false;
			}
		}
		return true;
	}
}
