package by.mpc.core.parse;

import by.mpc.core.model.ListRoute;

/**
 * Интерфейс для парсинга
 * @author SQL
 *
 */
public interface IParseRailway {
	ListRoute get(String bStation, String eStation);
}


