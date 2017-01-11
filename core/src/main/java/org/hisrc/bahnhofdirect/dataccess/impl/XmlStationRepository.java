package org.hisrc.bahnhofdirect.dataccess.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.hisrc.bahnhofdirect.dataccess.StationRepository;
import org.hisrc.bahnhofdirect.model.Station;
import org.hisrc.bahnhofdirect.model.Stations;

public class XmlStationRepository implements StationRepository {

	private final static String DEFAULT_RESOURCE_NAME = "de/db/noncd/iris/iris-tts/timetable/stations.xml";
	private List<Station> stations;
	private Set<String> knownDs100s;
	private Map<Integer, Station> stationsByEvaNr;

	public XmlStationRepository() {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(DEFAULT_RESOURCE_NAME)) {
			this.stations = loadStations(is);
			this.knownDs100s = createKnownDs100s(stations);
			this.stationsByEvaNr = createStationsByEvaNr(stations);
		} catch (IOException ioex) {
			throw new ExceptionInInitializerError(ioex);
		}
	}

	private Set<String> createKnownDs100s(List<Station> stations) {
		return Collections.unmodifiableSet(stations.stream().map(Station::getDs100).collect(Collectors.toSet()));
	}

	private Map<Integer, Station> createStationsByEvaNr(List<Station> stations) {
		return Collections
				.unmodifiableMap(stations.stream().collect(Collectors.toMap(Station::getEva, station -> station)));
	}

	@Override
	public List<Station> findAll() {
		return stations;
	}

	@Override
	public boolean knowsDs100(String ds100) {
		return knownDs100s.contains(ds100);
	}
	
	@Override
	public Station findByEvaNr(int evaNr) {
		return stationsByEvaNr.get(evaNr);
	}

	private List<Station> loadStations(InputStream is) throws IOException {
		try {
			JAXBContext context = JAXBContext.newInstance(Stations.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Stations stations = unmarshaller.unmarshal(new StreamSource(is), Stations.class).getValue();
			return Collections.unmodifiableList(stations.getStations());
		} catch (JAXBException jaxbex) {
			throw new IOException(jaxbex);
		}
	}
}
