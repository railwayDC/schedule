package by.mpc.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Глобальные данные для каждого из потоков
 * @author SQL
 *
 */
public class Data {

	
	public static ConfigurationProject config = new ConfigurationProject();
	// Карта данных Map<String, Object> для потока с определенным id 
	private Map<Long, Map<String, Object>> params = new HashMap<Long, Map<String, Object>>();
	// Делаем только один экземпляр
	private static Data data = new Data();
	// Добавляем для нового потока список параметров
	public static Data getInstance() {
		return data;
	}
	
	private Data() {
	}
	
	public synchronized void setParam(String key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException();
		}
		// id - потока
		long id = getId();
		if (!params.containsKey(id)) {
			params.put(id, new HashMap<String, Object>());
		}
		params.get(id).put(key, value);
	}
	
	public Object getParam(String key) {
		long id = getId();
		if (!params.containsKey(id)) {
			throw new IllegalStateException();
		}
		return params.get(id).get(key);
	}
	// Получение id потока
	private long getId() {
		return Thread.currentThread().getId();
	}
}
