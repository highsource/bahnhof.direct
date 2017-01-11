package org.hisrc.bahnhofdirect.dataccess.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.hisrc.bahnhofdirect.dataccess.HaltestelleRepository;
import org.hisrc.bahnhofdirect.dataccess.StationRepository;
import org.hisrc.bahnhofdirect.model.Haltestelle;
import org.hisrc.bahnhofdirect.model.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import gnu.trove.procedure.TIntProcedure;
import net.sf.jsi.Point;
import net.sf.jsi.Rectangle;
import net.sf.jsi.SpatialIndex;
import net.sf.jsi.rtree.RTree;

public class CsvHaltestelleRepository implements HaltestelleRepository {

	private final Logger LOGGER = LoggerFactory.getLogger(CsvHaltestelleRepository.class);

	private final StationRepository stationRepository = new XmlStationRepository();
	private final String DEFAULT_RESOURCE_NAME = "D_Bahnhof_2016_01_alle.csv";
	private final List<Haltestelle> haltestelles;
	private final Map<Integer, Haltestelle> haltestellesByEvaNr;
	private final SpatialIndex haltestellesSpatialIndex;

	public CsvHaltestelleRepository() {
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(DEFAULT_RESOURCE_NAME)) {
			this.haltestelles = loadHaltestelles(is);
			this.haltestellesByEvaNr = createHaltestellesByEvaNrMap(this.haltestelles);
			this.haltestellesSpatialIndex = createSpatialIndex(this.haltestelles);
		} catch (IOException ioex) {
			throw new ExceptionInInitializerError(ioex);
		}
	}

	private Map<Integer, Haltestelle> createHaltestellesByEvaNrMap(List<Haltestelle> haltestelles) {
		return Collections
				.unmodifiableMap(haltestelles.stream().collect(Collectors.toMap(Haltestelle::getEvaNr, h -> h)));
	}

	private List<Haltestelle> loadHaltestelles(InputStream is) throws IOException {
		final List<Haltestelle> haltestelles = new LinkedList<>();
		final CsvMapper mapper = new CsvMapper();
		final CsvSchema schema = mapper.schemaFor(Haltestelle.class).withHeader().withColumnSeparator(';');

		final MappingIterator<Haltestelle> haltestellesIterator = mapper.readerFor(Haltestelle.class).with(schema)
				.readValues(new InputStreamReader(is, "UTF-8"));
		while (haltestellesIterator.hasNext()) {
			try {
				final Haltestelle haltestelle = haltestellesIterator.next();
				if (haltestelle.getDs100() != null && !haltestelle.getDs100().isEmpty()) {

					if (stationRepository.knowsDs100(haltestelle.getDs100())) {
						haltestelles.add(haltestelle);
					} else {
						final Station station = stationRepository.findByEvaNr(haltestelle.getEvaNr());
						if (station != null) {
							haltestelles.add(new Haltestelle(haltestelle.getEvaNr(), station.getDs100(),
									haltestelle.getName(), haltestelle.getVerkehr(), haltestelle.getLaenge(),
									haltestelle.getBreite(), null));
						}
					}
				}
			} catch (RuntimeException rex) {
				LOGGER.warn("Could not read haltestelle from [{}].", haltestellesIterator.getCurrentLocation(), rex);
			}
		}
		return Collections.unmodifiableList(haltestelles);
	}

	private SpatialIndex createSpatialIndex(List<Haltestelle> haltestelles) {
		final SpatialIndex spatialIndex = new RTree();
		spatialIndex.init(null);
		haltestelles.forEach(haltestelle -> {
			float x = (float) haltestelle.getLaenge();
			float y = (float) haltestelle.getBreite();
			spatialIndex.add(new Rectangle(x, y, x, y), haltestelle.getEvaNr());
		});
		return spatialIndex;
	}

	@Override
	public List<Haltestelle> findAll() {
		return haltestelles;
	}

	@Override
	public Haltestelle findByEvaNr(int evaNr) {
		return haltestellesByEvaNr.get(evaNr);
	}

	@Override
	public Haltestelle findByLaengeAndBreite(double laenge, double breite) {

		final AtomicInteger result = new AtomicInteger(-1);
		haltestellesSpatialIndex.nearest(new Point((float) laenge, (float) breite), new TIntProcedure() {

			@Override
			public boolean execute(int value) {
				result.set(value);
				return true;
			}
		}, Float.POSITIVE_INFINITY);

		final int evaNr = result.get();
		return findByEvaNr(evaNr);
	}
}
