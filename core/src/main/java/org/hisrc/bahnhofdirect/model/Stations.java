package org.hisrc.bahnhofdirect.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="stations")
public class Stations {
	
	@XmlElement(name="station")
	private List<Station> stations;
	
	public List<Station> getStations() {
		return stations;
	}

}
