package by.mpc.web.tools.sort.comparator;

import java.util.Comparator;

import by.mpc.web.view.model.ListRouteView;

public class DepartureTimeComparator implements Comparator<ListRouteView> {

	@Override
	public int compare(ListRouteView o1, ListRouteView o2) {
		long eTime1 = o1.getExternRoute().getETime().getTimeInMillis(),
				eTime2 = o2.getExternRoute().getETime().getTimeInMillis();
		return (int) (eTime1 - eTime2);
	}

}
