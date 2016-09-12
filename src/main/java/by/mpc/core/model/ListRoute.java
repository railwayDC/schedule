package by.mpc.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Хранит список маршрутов
 * @author SQL
 *
 */
public class ListRoute implements Iterable<Route>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Route> list;
	
	public ListRoute() {
		list = new ArrayList<Route>();
	}
	
	public ListRoute(ListRoute list) {
		this();
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	public Route get(int index) {
		return list.get(index);
	}

	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void add(Route route) {
		list.add(route);		
	}

	public void addAll(ListRoute listRoute) {
		list.addAll(listRoute.list);
	}

	public List<Route> getList() {
		return Collections.unmodifiableList(list);
	}
	
	@Override
	public Iterator<Route> iterator() {
		return list.iterator();
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			s.append(list.get(i) + "\n");
		}
		return s.toString();
	}

}
