package by.mpc.core.correct;

import java.util.List;

import by.mpc.core.Data;

public abstract class AbstractCorrect implements ICorrect {
	
	protected String bStation;
	protected String eStation;
	protected List<String> stations;
	
	@SuppressWarnings("unchecked")
	public AbstractCorrect() {
		Data data = by.mpc.core.Data.getInstance();
		this.bStation = (String)data.getParam("station_start");
		this.eStation = (String)data.getParam("station_end");
		this.stations = (List<String>)data.getParam("stations");
	}
}
