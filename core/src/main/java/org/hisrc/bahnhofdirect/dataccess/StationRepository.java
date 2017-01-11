package org.hisrc.bahnhofdirect.dataccess;

import java.util.List;

import org.hisrc.bahnhofdirect.model.Station;

public interface StationRepository {
	
	public List<Station> findAll();
	public Station findByEvaNr(int evaNr);
	public boolean knowsDs100(String ds100);
}
