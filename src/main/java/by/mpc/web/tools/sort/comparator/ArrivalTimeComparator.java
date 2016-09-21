package by.mpc.web.tools.sort.comparator;

import java.util.Comparator;

import by.mpc.web.view.model.ListRouteView;

public class ArrivalTimeComparator implements Comparator<ListRouteView> {

	@Override
	public int compare(ListRouteView o1, ListRouteView o2) {
		long bTime1 = o1.getExternRoute().getBTime().getTimeInMillis(),
			bTime2 = o2.getExternRoute().getBTime().getTimeInMillis();	
		return (int) (bTime1 - bTime2);
	}

}
