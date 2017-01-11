package org.hisrc.bahnhofdirect.dataccess.impl;

import static org.junit.Assert.*;

import java.text.MessageFormat;

import org.hisrc.bahnhofdirect.dataccess.HaltestelleRepository;
import org.hisrc.bahnhofdirect.model.Haltestelle;
import org.junit.Test;

public class CsvHaltestelleRepositoryTest {

	private final HaltestelleRepository haltestelleRepository = new CsvHaltestelleRepository();

	@Test
	public void findsAll() {
		assertEquals(6538, haltestelleRepository.findAll().size());
	}

	@Test
	public void findsSeulbergById() {
		assertEquals("Seulberg", haltestelleRepository.findByEvaNr(8005544).getName());
	}

	@Test
	public void findsSeulbergByLonLat() {
		assertEquals("Seulberg", haltestelleRepository.findByLaengeAndBreite(8.657660, 50.239804).getName());
	}
	
	@Test
	public void findsFrankfurtNiederradByLonLat() {
		assertEquals("FNI", haltestelleRepository.findByLaengeAndBreite(8.637075, 50.081283).getDs100());
	}

	@Test
	public void findsByLonLat() {
		final long start = System.currentTimeMillis();
		final long count = 1000000;
		final double minx = 5.8667;
		final double maxx = 15.0419;
		final double miny = 47.2703;
		final double maxy = 55.0585;
		final double dx = maxx - minx;
		final double dy = maxy - miny;
		for (int index = 0; index < count; index++) {
			double x = minx + Math.random() * dx;
			double y = miny + Math.random() * dy;
			final Haltestelle result = haltestelleRepository.findByLaengeAndBreite(x, y);
			if (index % 10000 == 0)
			{
				System.out.println("Query for [" + x + ", " + y + "] resulted in [" + result.getName() + "].");
			}
			assertNotNull(result);
		}
		final long end = System.currentTimeMillis();
		final long time = end - start;
		System.out
				.println(MessageFormat.format("Took [{0}] ms for [{1}] queries, in average [{2}] ms per 1000 queries.",
						time, count, (1000 * time) / count));
	}
}
