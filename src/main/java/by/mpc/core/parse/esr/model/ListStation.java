package by.mpc.core.parse.esr.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListStation {
	private List<Station> stations;
	
	public ListStation() {
		stations = new ArrayList<>();
	}
	
	public ListStation(Collection<Station> stations) {
		this.stations = new ArrayList<>(stations);
	}
	
	public void add(Station station) {
		stations.add(station);	
	}
	
	public int size() {
		return stations.size();
	}
	
	public Station get(int index) {
		return stations.get(index);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Station station : stations) {
			str.append(station + "\n");
		}
		return str.toString();
	}
	
}
