package org.hisrc.bahnhofdirect.dataccess;

import org.hisrc.bahnhofdirect.model.Stop;
import org.hisrc.bahnhofdirect.model.StopResult;

public interface StopRepository {

	public Stop findById(String id);
	
	public StopResult findByLonLat(double lon, double lat);
}
