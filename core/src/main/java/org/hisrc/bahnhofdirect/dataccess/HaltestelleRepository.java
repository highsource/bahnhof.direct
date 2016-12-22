package org.hisrc.bahnhofdirect.dataccess;

import java.util.List;

import org.hisrc.bahnhofdirect.model.Haltestelle;

public interface HaltestelleRepository {

	public List<Haltestelle> findAll();
	
	public Haltestelle findByEvaNr(int evaNr);
	
	public Haltestelle findByLaengeAndBreite(double laenge, double breite);
}
