package org.hisrc.bahnhofdirect.dataccess.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hisrc.bahnhofdirect.dataccess.StopRepository;
import org.hisrc.bahnhofdirect.model.Stop;
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

public class CsvStopRepository implements StopRepository {

	private final Logger LOGGER = LoggerFactory.getLogger(CsvStopRepository.class);

	private final List<Stop> stops;
	private final Map<String, Stop> stopsById;
	private final SpatialIndex haltestellesSpatialIndex;

	public CsvStopRepository(InputStream is) {
		try {
			this.stops = loadStops(is);
			this.stopsById = createStopsByEvaNrMap(this.stops);
			this.haltestellesSpatialIndex = createSpatialIndex(this.stops);
		} catch (IOException ioex) {
			throw new ExceptionInInitializerError(ioex);
		} finally {
			try {
				is.close();
			} catch (IOException ioex) {
				throw new ExceptionInInitializerError(ioex);
			}
		}
	}

	private Map<String, Stop> createStopsByEvaNrMap(List<Stop> haltestelles) {
		return Collections
				.unmodifiableMap(haltestelles.stream().collect(Collectors.toMap(Stop::getId, Function.identity())));
	}

	private List<Stop> loadStops(InputStream is) throws IOException {
		final List<Stop> stops = new LinkedList<>();
		final CsvMapper mapper = new CsvMapper();
		final CsvSchema schema = mapper.schemaFor(Stop.class).withHeader();

		final MappingIterator<Stop> stopsIterator = mapper.readerFor(Stop.class).with(schema)
				.readValues(new InputStreamReader(is, "UTF-8"));
		while (stopsIterator.hasNext()) {
			try {
				final Stop stop = stopsIterator.next();
				stops.add(stop);
			} catch (RuntimeException rex) {
				LOGGER.warn("Could not read stop from [{}].", stopsIterator.getCurrentLocation(), rex);
			}
		}
		return Collections.unmodifiableList(stops);
	}

	private SpatialIndex createSpatialIndex(List<Stop> stops) {
		final SpatialIndex spatialIndex = new RTree();
		spatialIndex.init(null);
		for (int index = 0; index < stops.size(); index++) {
			final Stop stop = stops.get(index);
			float x = (float) stop.getLon();
			float y = (float) stop.getLat();
			spatialIndex.add(new Rectangle(x, y, x, y), index);
		}
		return spatialIndex;
	}

	@Override
	public List<Stop> findAll() {
		return stops;
	}

	@Override
	public Stop findById(String id) {
		return stopsById.get(id);
	}

	@Override
	public Stop findByLonLat(double lon, double lat) {
		final AtomicInteger result = new AtomicInteger(-1);
		haltestellesSpatialIndex.nearest(new Point((float) lon, (float) lat), new TIntProcedure() {

			@Override
			public boolean execute(int value) {
				result.set(value);
				return true;
			}
		}, Float.POSITIVE_INFINITY);

		final int index = result.get();
		return stops.get(index);
	}
}
