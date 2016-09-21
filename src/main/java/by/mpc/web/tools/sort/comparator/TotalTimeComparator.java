package by.mpc.web.tools.sort.comparator;

import java.util.Comparator;

import by.mpc.web.view.model.ListRouteView;

public class TotalTimeComparator implements Comparator<ListRouteView> {

	@Override
	public int compare(ListRouteView o1, ListRouteView o2) {
		int totalTime1 = o1.getExternRoute().getTotalTime(),
				totalTime2 = o2.getExternRoute().getTotalTime();
		return totalTime1 - totalTime2;
	}

}
