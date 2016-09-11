package by.mpc.core.parse;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;
import by.mpc.core.parse.cash.CashManager;

public class FileParseRailway implements IParseRailway {

	private String date;

	public FileParseRailway(String date) {
		this.date = date;
	}

	@Override
	public ListRoute get(String bStation, String eStation) {
		ListRoute listRoute = new ListRoute();
		// Получаем кэш
		ListRoute cash = CashManager.load(date, bStation);
		if (cash != null) {
			// Перебор кэша
			for (Route route : cash) {
				if (route.getEEnterStation().equals(eStation)) {
					listRoute.add(route);
				}
			}
		}
		return listRoute;
	}

}
