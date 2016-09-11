package by.mpc.core.parse.esr.core;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.mpc.core.parse.esr.model.ListStation;
import by.mpc.core.parse.esr.model.Station;
import by.mpc.core.parse.esr.model.StationColumnEnum;

public class ESRLoader {

	public static ListStation getListStation(String text, Properties properties) {
		ListStation listStation = new ListStation();
		String pattern = properties.getProperty("database.pattern");
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);

		while (m.find()) {
			Station station = new Station();
			station.setParam(StationColumnEnum.DIVISION, m.group(1));
			station.setParam(StationColumnEnum.ESR, m.group(2));
			station.setParam(StationColumnEnum.COUNTRY, m.group(3));
			station.setParam(StationColumnEnum.REGION, m.group(4));
			station.setParam(StationColumnEnum.RAILWAY, m.group(5));
			station.setParam(StationColumnEnum.TYPE, m.group(6));
			station.setParam(StationColumnEnum.NAME, m.group(7));
			listStation.add(station);
		}

		return listStation;
	}
}
