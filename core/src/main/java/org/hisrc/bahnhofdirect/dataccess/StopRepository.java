package org.hisrc.bahnhofdirect.dataccess;

import java.util.List;

import org.hisrc.bahnhofdirect.model.Stop;

public interface StopRepository {

	public List<Stop> findAll();
	
	public Stop findById(String id);
	
	public Stop findByLonLat(double lon, double lat);
}
