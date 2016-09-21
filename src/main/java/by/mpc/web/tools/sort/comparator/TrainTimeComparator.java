package by.mpc.web.tools.sort.comparator;

import java.util.Comparator;

import by.mpc.web.view.model.ListRouteView;

public class TrainTimeComparator implements Comparator<ListRouteView>  {

	@Override
	public int compare(ListRouteView o1, ListRouteView o2) {
		int trainTime1 = o1.getExternRoute().getTrainTime(),
				trainTime2 = o2.getExternRoute().getTrainTime();
		return trainTime1 - trainTime2;
	}

}
