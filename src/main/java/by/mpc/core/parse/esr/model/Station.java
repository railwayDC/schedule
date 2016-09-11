package by.mpc.core.parse.esr.model;

public class Station {
	private String division;
	private String esr;
	private String country;
	private String region;
	private String type;
	private String name;
	private String railway;

	public void setParam(StationColumnEnum column, String value) {
		switch (column) {
		case COUNTRY:
			country = value;
			break;
		case DIVISION:
			division = value;
			break;
		case ESR:
			esr = value;
			break;
		case NAME:
			name = value;
			break;
		case RAILWAY:
			railway = value;
		case REGION:
			region = value;
			break;
		case TYPE:
			type = value;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String getParam(StationColumnEnum column) {
		switch (column) {
		case COUNTRY:
			return country;
		case DIVISION:
			return division;
		case ESR:
			return esr;
		case NAME:
			return name;
		case RAILWAY:
			return railway;
		case REGION:
			return region;
		case TYPE:
			return type;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public String toString() {
		return "Station [division=" + division + ",esr=" + esr + ", country=" + country + ", region=" + region
				+ ", type=" + type + ", name=" + name + ", railway=" + railway + "]";
	}
}
