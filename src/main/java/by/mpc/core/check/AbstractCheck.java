package by.mpc.core.check;

import java.util.List;

import by.mpc.core.Data;

public abstract class AbstractCheck implements ICheck {
	
	protected String bEnterStation;
	protected String eEnterStation;
	protected List<String> stations;
	
	@SuppressWarnings("unchecked")
	public AbstractCheck() {
		Data data = by.mpc.core.Data.getInstance();
		this.bEnterStation = (String)data.getParam("input.station_start");
		this.eEnterStation = (String)data.getParam("input.station_end");
		this.stations = (List<String>)data.getParam("input.stations");
	}

}
