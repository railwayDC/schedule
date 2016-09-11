package by.mpc.core.correct;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public interface ICorrect {
	boolean is(ListRoute trace, Route route);
}
