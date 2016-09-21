package by.mpc.web.view.model;

import by.mpc.core.model.Route;

public class RouteView extends Route {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int trainTime;
	private int stationTime;
	private int totalTime;

	public RouteView(Route route) {
		super(route);
	}
	
	public RouteView(Route currentRoute, Route nextRoute) {
		super(currentRoute);
		// Получаем дополнительные данные
		// Время в поезде
		trainTime = (int) (eDateTime.getTimeInMillis() - bDateTime.getTimeInMillis()) / 60000;
		// Время на станции
		if (nextRoute == null) {
			stationTime = 0;
		} else {
			stationTime = (int) (nextRoute.getBTime().getTimeInMillis() - eDateTime.getTimeInMillis()) / 60000;
		}
		// Общее время
		totalTime = stationTime + trainTime;
	}
	
	public int getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(int trainTime) {
		this.trainTime = trainTime;
	}

	public int getStationTime() {
		return stationTime;
	}

	public void setStationTime(int stationTime) {
		this.stationTime = stationTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	// Получение текстового выражения времени из количества минут
	public String getTextTime(int minutes) {
		String time = "";
		int h = minutes / 60, m = minutes % 60;
		if (h != 0) {
			time += h + " ч";
		}
		if (m != 0) {
			time += " " + m + " мин";
		}
		return time;
	}

	@Override
	public String toString() {
		return super.toString() + " trainTime = " + trainTime + " stationTime " + stationTime + " totalTime " + totalTime;
	}
}
