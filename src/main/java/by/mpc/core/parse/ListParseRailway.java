package by.mpc.core.parse;

import java.util.ArrayList;
import java.util.List;

import by.mpc.core.model.ListRoute;

/**
 * Класс для парсинга данных с различных источников
 * @author SQL
 *
 */
public class ListParseRailway {
	private List<IParseRailway> listParseRailway;
	
	public ListParseRailway() {
		listParseRailway = new ArrayList<IParseRailway>();
	}
	
	public void register(IParseRailway parseRailway) {
		listParseRailway.add(parseRailway);
	}
	
	public void remove(IParseRailway parseRailway) {
		listParseRailway.remove(parseRailway);
	}
	
	public ListRoute get(String bStation, String eStation) {
		ListRoute listRoute = null;
		for (IParseRailway parseRailway : listParseRailway) {
			listRoute = parseRailway.get(bStation, eStation);
			if (listRoute != null && !listRoute.isEmpty()) {
				break;
			}
		}
		return listRoute;
	}
}
