package org.hisrc.bahnhofdirect.dataccess;

import org.hisrc.bahnhofdirect.model.Agency;
import org.hisrc.bahnhofdirect.model.Stop;

public interface AgencyStopRepository {
	
	public Agency findAgencyById(String agencyId);
	
	public Stop findNearestStopByAgencyIdAndLonLat(String agencyId, double lon, double lat);

}
