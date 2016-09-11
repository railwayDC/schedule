package by.mpc.core.parse;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.mpc.core.model.ListRoute;
import by.mpc.core.model.Route;

public class XMLParseRailway implements IParseRailway {

	private InputStream is;
	private DocumentBuilder builder;
	
	public XMLParseRailway(InputStream is) {
		this.is = is;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ListRoute get(String bStation, String eStation)  {
		ListRoute listRoute = new ListRoute();

		try {
			Document doc = builder.parse(is);
			NodeList listArrival = doc.getElementsByTagName("arrival");
			NodeList listDeparture = doc.getElementsByTagName("departure");
			
			Properties properties = new Properties();
			properties.setProperty("input.station_start", bStation);
			properties.setProperty("input.station_end", eStation);
			properties.setProperty("station_start", bStation);
			properties.setProperty("station_end", eStation);
			
			for (int i = 0; i < listArrival.getLength(); i++) {
				properties.setProperty("input.time_start", listArrival.item(i).getTextContent());
				properties.setProperty("input.time_end", listDeparture.item(i).getTextContent());
				listRoute.add(new Route(properties));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return listRoute;
	}

}
