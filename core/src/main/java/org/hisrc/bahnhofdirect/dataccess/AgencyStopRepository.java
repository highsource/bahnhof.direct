package org.hisrc.bahnhofdirect.dataccess;

import org.hisrc.bahnhofdirect.model.Agency;
import org.hisrc.bahnhofdirect.model.StopResult;

public interface AgencyStopRepository {
	
	public Agency findAgencyById(String agencyId);
	
	public StopResult findNearestStopByAgencyIdAndLonLat(String agencyId, double lon, double lat);

}
