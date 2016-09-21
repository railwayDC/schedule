package by.mpc.web.tools.sort;

import java.util.Collections;
import java.util.Comparator;

import by.mpc.web.tools.sort.comparator.ArrivalTimeComparator;
import by.mpc.web.tools.sort.comparator.CountTrainComparator;
import by.mpc.web.tools.sort.comparator.DepartureTimeComparator;
import by.mpc.web.tools.sort.comparator.StationTimeComparator;
import by.mpc.web.tools.sort.comparator.TotalTimeComparator;
import by.mpc.web.tools.sort.comparator.TrainTimeComparator;
import by.mpc.web.view.model.ListRouteView;
import by.mpc.web.view.model.ScheduleView;

public class ScheduleSort {

	public static ScheduleView sort(ScheduleView schedule, TypeSortEnum typeSort) {
		
		Comparator<ListRouteView> comparator = null;
		// Тип сортировки
		switch (typeSort) {
		case COUNT_TRAIN:
			comparator = new CountTrainComparator();
			break;
		case DEPARTURE_TIME:
			comparator = new DepartureTimeComparator();
			break;
		case ARRIVAL_TIME:
			comparator = new ArrivalTimeComparator();
			break;
		case STATION_TIME:
			comparator = new StationTimeComparator();
			break;
		case TRAIN_TIME:
			comparator = new TrainTimeComparator();
			break;
		case TOTAL_TIME:
			comparator = new TotalTimeComparator();
			break;
		}
		// Сортировка
		if (comparator != null) {
			Collections.sort(schedule.getList(), comparator);
		}
		return schedule;
	}
}
