package org.hisrc.bahnhofdirect.dataccess.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.hisrc.bahnhofdirect.dataccess.HaltestelleRepository;
import org.hisrc.bahnhofdirect.dataccess.StationRepository;
import org.junit.Test;

public class XmlStationRepositoryTest {

	private final StationRepository stationRepository = new XmlStationRepository();

	@Test
	public void findsAllStations() {
		assertFalse(stationRepository.findAll().isEmpty());
	}

	@Test
	public void findsByDs100() {
		assertTrue(stationRepository.knowsDs100("FNI"));
	}

	@Test
	public void findsByEvaNr() {
		assertNotNull(stationRepository.findByEvaNr(8002050));
	}
}
