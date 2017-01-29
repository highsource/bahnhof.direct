package org.hisrc.bahnhofdirect.dataccess.impl;

import static org.junit.Assert.assertEquals;

import org.hisrc.bahnhofdirect.dataccess.StopRepository;
import org.junit.Test;

public class CsvStopRepositoryTest {

	private StopRepository stopRepository = new CsvStopRepository(
			getClass().getClassLoader().getResourceAsStream("db/stops.txt"));

	@Test
	public void findsSeulbergById() {
		assertEquals("Seulberg", stopRepository.findById("8005544").getName());
	}

	@Test
	public void findsSeulbergByLonLat() {
		assertEquals("Seulberg", stopRepository.findByLonLat(8.657660, 50.239804).getStop().getName());
	}

	@Test
	public void findsFrankfurtNiederradByLonLat() {
		assertEquals("8002050", stopRepository.findByLonLat(8.637075, 50.081283).getStop().getId());
	}
}
