package by.mpc.web.tools.sort.comparator;

import java.util.Comparator;

import by.mpc.web.view.model.ListRouteView;

public class CountTrainComparator implements Comparator<ListRouteView> {

	@Override
	public int compare(ListRouteView o1, ListRouteView o2) {
		int countTrain1 = Integer.valueOf(o1.getExternRoute().getNumberTrain()),
				countTrain2 = Integer.valueOf(o2.getExternRoute().getNumberTrain());
		return countTrain1 - countTrain2;
	}

}
